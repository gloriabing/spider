package org.gloria.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Create on 2017/3/8 17:49.
 *
 * 爬虫抓取种子配置
 * 
 * @author : gloria.
 */
public class Seed {

    /**
     * 待抓取的链接
     */
    private static String url;
    /**
     * 失败重试次数
     */
    private static Integer retry;
    /**
     * 请求头信息
     */
    private static Map<String, String> headers;
    
    private static final Integer DEFAULT_RETRY = 3;
    
    
    public static Seed me() {
        return new Seed();
    }

    public static Seed me(String url) {
        return new Seed().url(url).retry(DEFAULT_RETRY);
    }

    public Seed() {
        this.headers = new HashMap<>();
    }
    
    public Seed url(String url) {
        this.url = url;
        return this;
    }

    public Seed retry(Integer retry) {
        this.retry = retry;
        return this;
    }

    public Seed headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public Seed header(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public String url() {
        return this.url;
    }

    public Integer retry() {
        return this.retry;
    }
}
