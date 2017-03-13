package org.gloria.scheduler;

import org.gloria.config.Seed;
import org.gloria.manager.CrawlUrl;
import org.gloria.manager.UrlManager;
import org.gloria.spider.Crawl;
import org.gloria.spider.ISpider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create on 2017/3/9 15:45.
 *
 * 调度器
 * 
 * @author : gloria.
 */
public class Scheduler {
    private static final Logger LOG = LoggerFactory.getLogger(Scheduler.class);

    private ISpider spider;
    private Seed seed;
    private ExecutorService executor;

    public Scheduler(String url, ISpider spider) {
        this.spider = spider;
        this.seed = spider.init();
        if (this.seed == null) {
            this.seed = Seed.me();
        }
        init(url);
    }

    //init
    public void init(String url) {
        //将url放入队列中
        UrlManager.push(url);
        this.seed.url(url);
        
        //根据配置创建对应大小的线程池
        int thread = seed.thread();
        executor = Executors.newFixedThreadPool(thread);
    }

    //run
    public void running() {
        
        while (UrlManager.isNotEmpty()) {
            CrawlUrl url = UrlManager.pop();
            Runnable runnable = new Crawl(this.seed, this.spider, url);
            executor.execute(runnable);

        }
        
    }
    
}
