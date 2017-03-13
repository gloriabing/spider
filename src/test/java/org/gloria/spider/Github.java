package org.gloria.spider;

import org.gloria.config.Response;
import org.gloria.config.Seed;
import org.gloria.config.Template;
import org.gloria.config.Type;
import org.gloria.scheduler.Scheduler;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;


/**
 * Create on 2017/3/13 17:08.
 *
 * github 抓取测试
 * @author : gloria.
 */
public class Github implements ISpider {
    private static final Logger LOG = LoggerFactory.getLogger(Github.class);
    
    @Override
    public Seed init() {
        return Seed.me();
    }
    
    @Override
    public void parse(Response response) {
        Template template = Template.me().type(Type.HTML).expression("a.v-align-middle");
        response.template(template);
        Iterator<Element> elements = response.domParse().iterator();
        while (elements.hasNext()) {
            LOG.info("parse text O(∩_∩)O  => {}", elements.next().text());
        }
    }

    @Override
    public List<String> discoverMore(Response response) {
        return null;
    }

    public static void main(String[] args) {
        new Scheduler("https://github.com/search?utf8=%E2%9C%93&q=java", new Github()).running();
    }
}