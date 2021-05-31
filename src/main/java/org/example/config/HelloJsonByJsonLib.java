package org.example.config;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.example.IO;

import java.util.HashMap;
import java.util.Map;


public class HelloJsonByJsonLib {
    public static void main(String[] args) {
        final String text = IO.readResourceText("/demo.json");
        assert null != text;

        // Map转JSON对象
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("gender", false);
        JSONObject mapJson = JSONObject.fromObject(map);
        System.out.println(mapJson);

        // 字符串转JSON对象
        JSONObject textJson = JSONObject.fromObject(text);
        System.out.println(textJson);
        System.out.println(textJson.getString("key"));
        System.out.println(textJson.getDouble("PI"));
        JSONArray arr = textJson.getJSONArray("arr");
        System.out.println(arr.get(1));
        JSONObject obj = textJson.getJSONObject("obj");
        System.out.println(obj.getInt("x"));
        System.out.println(obj.getInt("y"));

        // 因为JSONObject实现了Map接口，所以可以像操作Map一样操作JSONObject
        // 编辑JSONObject
        obj.put("z", 789);
        System.out.println(obj);

        // JSON对象转化为字符串
        String resultText = textJson.toString();
        System.out.println(resultText);
    }
}
