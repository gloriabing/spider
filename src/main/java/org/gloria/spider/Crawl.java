package org.gloria.spider;

import org.gloria.config.Response;
import org.gloria.config.Seed;
import org.gloria.manager.CrawlUrl;
import org.gloria.manager.UrlManager;
import org.gloria.util.Http;
import org.gloria.util.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create on 2017/3/9 15:44.
 *
 * @author : gloria.
 */
public class Crawl implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(Crawl.class);

    private Seed seed;
    private ISpider spider;
    private CrawlUrl crawlUrl;

    public Crawl(Seed seed, ISpider spider, CrawlUrl crawlUrl) {
        this.seed = this.seed == null ? Seed.me() : seed;
        this.spider = spider;
        this.crawlUrl = crawlUrl;
    }

    @Override
    public void run() {

        Map<String, String> headers = new HashMap<>();
        if (!seed.headers().isEmpty()) {
            headers = seed.headers();
            LOG.info("add specific http headers => {}", Json.object2json(headers));
        }

        //请求链接响应内容
        String url = crawlUrl.url();
        String body = null;
        try {
            body = Http.doGet(url, null, headers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Response response = Response.me().body(body);
        response.url(url);

        //解析当前页面内容
        spider.parse(response);

        
        //发现更多内容
        List<String> moreUrls = spider.discoverMore(response);
        if (moreUrls != null && !moreUrls.isEmpty()) {
            UrlManager.batchPush(moreUrls);
        }

    }

}
