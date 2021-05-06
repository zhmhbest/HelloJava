package org.example.config;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.example.IO;


public class HelloJsonByJsonSimple {
    public static void main(String[] args) {
        final String text = IO.readResourceText("/demo.json");
        assert null != text;

        JSONObject json = (JSONObject) JSONValue.parse(text);
        System.out.println(json);
        System.out.println(json.get("key"));
        System.out.println(json.get("PI"));

        JSONArray arr = (JSONArray) json.get("arr");
        System.out.println(arr.get(1));

        JSONObject obj = (JSONObject) json.get("obj");
        System.out.println(obj.get("x"));
        System.out.println(obj.get("y"));

        obj.put("z", 789);
        System.out.println(obj);

        String resultJson = json.toString();
        System.out.println(resultJson);
    }
}
