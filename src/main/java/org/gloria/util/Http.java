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

/**
 * Create on 2017/3/8 15:24.
 *
 * @author : gloria.
 */
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
    

    public static String doGet(String url, String charset) throws IOException {
        new Http().httpClient().httpGet(url);
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
    
    public static String doGet(String url) throws IOException {
        return doGet(url, null);
    }
}
