package org.gloria.manager;

import org.gloria.util.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Create on 2017/3/9 16:04.
 *
 * 抓取url管理
 * 
 * @author : gloria.
 */
public class UrlManager {
    private static final Logger LOG = LoggerFactory.getLogger(UrlManager.class);

    /**
     * 用set进行url去重
     */
    private static Set<String> urlSet = new HashSet<>();
    private static Queue<CrawlUrl> urlQueue = new LinkedBlockingQueue<>();

    public static boolean isEmpty() {
        return urlQueue == null || urlQueue.isEmpty();
    }

    public static boolean isNotEmpty() {
        return !isEmpty();
    }

    public static void push(String url) {

        CrawlUrl crawlUrl = CrawlUrl.me(url);
        if (!urlQueue.contains(crawlUrl) && !urlSet.contains(url)) {
            // push url
            LOG.info("push new url => {}", url);
            urlQueue.add(crawlUrl);
            urlSet.add(url);
        }
        
    }

    public static void batchPush(List<String> urls) {
        for (String url : urls) {
            push(url);
        }
    }

    public static CrawlUrl pop() {
        LOG.info("pop one => {}", Json.object2json(urlQueue.peek()));
        
        return urlQueue.remove();
    }
    
}
