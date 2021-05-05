package org.example.config;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.example.IO;

public class HelloJson {
    public static void main(String[] args) {
        final String text = IO.readResourceText("/demo.json");
        assert null != text;

        JSONObject json = JSONObject.fromObject(text);
        System.out.println(json.getString("key"));
        System.out.println(json.getDouble("PI"));
        JSONArray arr = json.getJSONArray("arr");
        System.out.println(arr.get(1));
        JSONObject obj = json.getJSONObject("obj");
        System.out.println(obj.getInt("x"));
        System.out.println(obj.getInt("y"));

        obj.put("z", 789);
        System.out.println(obj);

        String resultJson = json.toString();
        System.out.println(resultJson);
    }
}
