package org.gloria.spider;

import org.apache.commons.lang3.StringUtils;
import org.gloria.config.Response;
import org.gloria.config.Seed;
import org.gloria.config.Template;
import org.gloria.config.Type;
import org.gloria.scheduler.Scheduler;
import org.gloria.util.Regex;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Create on 2017/3/27 22:14.
 *
 * @author : gloria.
 */
public class LianJiaXiaoQu implements ISpider {

    private static final Logger LOG = LoggerFactory.getLogger(LianJiaXiaoQu.class);

    @Override
    public Seed init() {
        return Seed.me();
    }
    
    @Override
    public void parse(Response response) {
        Template template = Template.me().type(Type.HTML).expression(".listContent > li > div.info>div.title > a");
        response.template(template);
        Iterator<Element> elements = response.domParse().iterator();
        while (elements.hasNext()) {
            Element element = elements.next();
            LOG.info("parse text O(∩_∩)O  => {}", element.attr("href") + "\t" + element.text());
        }
    }

    @Override
    public List<String> discoverMore(Response response) {
        String page = Regex.match(response.url(), "^http://bj\\.lianjia\\.com/xiaoqu/pg([\\d]+)/");

        if (StringUtils.isNotBlank(page) && page.equals("100")) {
            Scheduler.shutdown();
        }

        String newUrl = "http://bj.lianjia.com/xiaoqu/pg" + (Integer.parseInt(page) + 1) + "/";
        return new ArrayList<String>() {{
            add(newUrl);
        }};
    }

    @Override
    public boolean shutdown() {
        return false;
    }

    public static void main(String[] args) {
        new Scheduler("http://bj.lianjia.com/xiaoqu/pg1/", new LianJiaXiaoQu()).running();
    }
}
