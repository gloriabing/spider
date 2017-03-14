package org.gloria.spider;

import org.gloria.config.Response;
import org.gloria.config.Seed;

import java.util.List;

/**
 * Create on 2017/3/13 15:44.
 *
 * @author : gloria.
 */
public interface ISpider {

    /**
     * 初始化爬虫配置
     *
     * @return
     */
    Seed init();

    /**
     * 解析内容
     *
     * @param response
     */
    void parse(Response response);

    /**
     * 发现更多内容
     *
     * @return
     */
    List<String> discoverMore(Response response);

    /**
     * 停止爬虫
     * @return
     */
    boolean shutdown();

}
