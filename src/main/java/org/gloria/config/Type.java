package org.gloria.config;

/**
 * Create on 2017/3/9 15:59.
 * 
 * 模板类型
 *
 * @author : gloria.
 */
public enum Type {
    
    JOSN(1),
    HTML(2),
    REGULAR_EXPRESSION(3);

    private Integer type;

    private Type(Integer type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }
    
}
