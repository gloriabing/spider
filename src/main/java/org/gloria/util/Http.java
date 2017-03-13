package org.gloria.util;


import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Create on 2017/3/8 15:24.
 *
 * http 处理封装
 * 
 * 
 * @author : gloria.
 */
//TODO 规范异常处理
public class Http {

    private static CloseableHttpClient client;
    private static HttpGet httpGet;
    private static CloseableHttpResponse response;

    private static final String DEFAULT_CHARSET = "utf-8";

    private Http httpClient() {
        if (client == null) {
            client = HttpClients.createDefault();
        }
        return this;
    }

    private Http httpGet(String url) {
        if (httpGet == null) {
            httpGet = new HttpGet(url);
        }
        return this;
    }

    /**
     * get请求
     * @param url
     *      待请求的链接
     * @param charset
     *      目标页面字符集
     * @param headers
     *      指定请求头信息
     * @return
     *      链接对应返回的内容
     * @throws IOException
     */
    public static String doGet(String url, String charset, Map<String, String> headers) throws IOException {
        new Http().httpClient().httpGet(url);
        if (headers != null && !headers.isEmpty()) {
            for (String key : headers.keySet()) {
                httpGet.addHeader(key, headers.get(key));
            }
        }
        response = client.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                Charset tempCharset = ContentType.getOrDefault(entity).getCharset();
                if (StringUtils.isBlank(charset)) {
                    if (tempCharset != null) {
                        charset = tempCharset.name();
                    } else {
                        charset = DEFAULT_CHARSET;
                    }
                }
                return EntityUtils.toString(entity, charset);
            }
        }
        return null;
    }

    /**
     * get请求
     * @param url
     *      待请求的链接
     * @param charset
     *      目标页面字符集
     * @return
     *      链接对应返回的内容
     * @throws IOException
     */
    public static String doGet(String url, String charset) throws IOException {
        return doGet(url, charset, new HashMap<>());
    }

    public static String doGet(String url) throws IOException {
        return doGet(url, null);
    }
}
