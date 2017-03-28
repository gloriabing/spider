package org.gloria.config;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Create on 2017/3/9 16:21.
 *
 * @author : gloria.
 */
public class Response {

    private static String url;
    private static String body;
    private Template template;

    public static Response me() {
        return new Response();
    }
    
    public Response body(String body) {
        this.body = body;
        return this;
    }

    public String body() {
        return this.body();
    }

    public Response url(String url) {
        this.url = url;
        return this;
    }
    
    public String url() {
        return this.url;
    }

    public Response template(Template template) {
        this.template = template;
        return this;
    }
    public Document document() {
        return Jsoup.parse(body);
    }

    /**
     * dom解析
     * @param selector
     *          css选择器表达式
     * @return
     *          符合条件的元素集合
     */
    private Elements elements(String selector) {
        return document().select(selector);
    }

    public Elements domParse() {
        if (template.type() == Type.HTML) {
            return elements(template.expression());
        }
        return new Elements();
    }
        
}
