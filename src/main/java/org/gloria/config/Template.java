package org.gloria.config;

/**
 * Create on 2017/3/9 15:55.
 *
 * 模板配置
 * 
 * @author : gloria.
 */
public class Template {

    /**
     * 模板类型
     *      json/html/regex...
     */
    private Type type;
    /**
     * 具体模板表达式
     */
    private String expression;

    public Template() {
    }

    public Template me() {
        return new Template();
    }

    public Template me(Type type, String expression) {
        return new Template().type(type).expression(expression);
    }

    public Template type(Type type) {
        this.type = type;
        return this;
    }

    public Template expression(String expression) {
        this.expression = expression;
        return this;
    }

    public Type type() {
        return this.type;
    }

    public String expression() {
        return this.expression;
    }
    
}
