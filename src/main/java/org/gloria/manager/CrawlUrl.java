package org.gloria.manager;


/**
 * Create on 2017/3/9 15:49.
 *
 * @author : gloria.
 */
public class CrawlUrl {

    private String url;


    public CrawlUrl() {
    }

    public static CrawlUrl me(String url) {
        return new CrawlUrl().url(url);
    }

    public CrawlUrl url(String url) {
        this.url = url;
        return this;
    }

    public String url() {
        return this.url;
    }
    
    
}
