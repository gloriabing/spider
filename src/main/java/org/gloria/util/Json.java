package org.gloria.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Create on 2017/3/9 16:25.
 * <p>
 * json解析工具
 *
 * @author : gloria.
 */
public class Json {

    private static Gson gson = new Gson();
    private static JsonParser parser = new JsonParser();

    public static String object2json(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T json2object(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);        
    }

    public static JsonObject extractJsonObject(String json, String key) {
        return parser.parse(json).getAsJsonObject().get(key).getAsJsonObject();
    }

    public static JsonArray extractJsonArray(String json, String key) {
        return parser.parse(json).getAsJsonObject().get(key).getAsJsonArray();
    }

    public static JsonObject formatJsonObject(String json) {
        return parser.parse(json).getAsJsonObject();
    }

    public static JsonArray formatJsonArray(String json) {
        return parser.parse(json).getAsJsonArray();
    }

    public static JsonObject getAsJsonObject(JsonObject jsonObject, String key) {
        return jsonObject.get(key).getAsJsonObject();
    }

    public static JsonArray getAsJsonArray(JsonObject jsonObject, String key) {
        return jsonObject.get(key).getAsJsonArray();
    }

    public static String getAsString(JsonObject jsonObject, String key) {
        return jsonObject.get(key).getAsString();
    }

    public static Integer getAsInt(JsonObject jsonObject, String key) {
        return jsonObject.get(key).getAsInt();
    }
    
}
