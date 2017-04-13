package org.gloria.scheduler;

import org.gloria.config.Seed;
import org.gloria.manager.CrawlUrl;
import org.gloria.manager.UrlManager;
import org.gloria.spider.Crawl;
import org.gloria.spider.ISpider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create on 2017/3/9 15:45.
 * <p>
 * 调度器
 *
 * @author : gloria.
 */
public class Scheduler {
    private static final Logger LOG = LoggerFactory.getLogger(Scheduler.class);

    private ISpider spider;
    private Seed seed;
    private static ExecutorService executor;

    public Scheduler(String url, ISpider spider) {
        this.spider = spider;
        this.seed = spider.init();
        if (this.seed == null) {
            this.seed = Seed.me();
        }
        init(url);
    }

    public Scheduler(List<String> urlList, ISpider spider) {
        this.spider = spider;
        this.seed = spider.init();
        if (this.seed == null) {
            this.seed = Seed.me();
        }
        init(urlList);
        
    }

    public Scheduler(ISpider spider) {
        this.spider = spider;
        if (this.seed == null) {
            this.seed = Seed.me();
        }
        init();
    }

    //init
    public void init(String url) {
        //将url放入队列中
        UrlManager.push(url);

        init();
    }

    public void init(List<String> urls) {
        //将url放入队列中
        for (String url : urls) {
            UrlManager.push(url);
        }

        init();
    }

    public void init() {
        //根据配置创建对应大小的线程池
        int thread = seed.thread();
        executor = Executors.newFixedThreadPool(thread);
    }

    //run
    public void running() {

        while (UrlManager.isNotEmpty()) {
            if (spider.shutdown()) {
                shutdown();
                break;
            }
            CrawlUrl url = UrlManager.pop();
            Runnable runnable = new Crawl(this.seed, this.spider, url);
            executor.execute(runnable);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        waitForStop();

    }

    //wait for stop
    public void waitForStop() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (UrlManager.isNotEmpty()) {
                running();
                break;
            } 
        }

    }
    
    //停止调度
    public static void shutdown() {

        if (!executor.isShutdown()) {
            //关闭线程池
            executor.shutdown();
            LOG.info("finished !");
            System.exit(0);
        }
        
    }

}
