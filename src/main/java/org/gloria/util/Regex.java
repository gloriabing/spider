package org.gloria.util;

import com.google.common.base.CharMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create on 2017/3/9 17:41.
 * <p>
 * 正则处理工具类
 *
 * @author : gloria.
 */
public class Regex {

    public static String match(String text, String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static List<String> matches(String text, String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(text);
        int count = matcher.groupCount();

        List<String> list = new ArrayList<>();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                list.add(CharMatcher.WHITESPACE.trimFrom(matcher.group(count)));
            }
        }
        return list;
    }
    
    
    
}
