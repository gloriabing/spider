# Java简易爬虫框架

核心是实现ISpider接口,实现对应的方法即可进行简单抓取操作

#### 解析页面内容

```java
    
    /**
     * 解析内容
     *
     * @param response
     */
    void parse(Response response);
    

```

例：

```java

    @Override
    public void parse(Response response) {
        //创建抓取模板
        Template template = Template.me().type(Type.HTML).expression("a.v-align-middle");
        response.template(template);
        //根据模板配置，提取对应内容
        Iterator<Element> elements = response.domParse().iterator();
        while (elements.hasNext()) {
            LOG.info("parse text O(∩_∩)O  => {}", elements.next().text());
        }
    }

```

#### 发现更多内容

```java
    
    /**
     * 发现更多内容
     *
     * @return
     */
    List<String> discoverMore(Response response);

```

例：

```java

    @Override
    public List<String> discoverMore(Response response) {
        //配置模板
        Template template = Template.me().type(Type.HTML).expression("a.next_page");
        response.template(template);
        //根据模板解析
        Element element = response.domParse().first();
        String url = element.attr("href");
        url = "https://github.com" + url;
        String finalUrl = url;
        if (Regex.match(finalUrl, "^https://github\\.com/search\\?p=([\\d]+)\\&").equals("2")) {
            Scheduler.shutdown();
        }
        //将提取出的链接放回调度当中
        return new ArrayList<String>() {{
            add(finalUrl);
        }};
    }

```

#### 启动方式

```java
    
    public static void main(String[] args) {
            new Scheduler("https://github.com/search?utf8=%E2%9C%93&q=java", new Github()).running();
    }

```