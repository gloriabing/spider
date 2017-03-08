package org.gloria.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Create on 2017/3/8 17:02.
 *
 * @author : gloria.
 */
public class HttpTest {
    @Test
    public void doGet() throws Exception {
        String url = "http://www.baidu.com";
        System.out.println(Http.doGet(url));
        
    }

}