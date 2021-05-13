package org.example.http;

import org.apache.http.util.EntityUtils;
import java.util.Arrays;
import java.util.HashMap;


public class HelloHttpClient {

    public static void testGet() {
        HashMap<String, Object> reqParams = new HashMap<>(8);
        reqParams.put("key1", "val1");
        HashMap<String, String> reqHeaders = new HashMap<>(8);
        reqHeaders.put("key2", "val2");
        //
        HttpUtil.get("http://127.0.0.1:5000/request", reqHeaders, reqParams, (status, headers, entity) -> {
            System.out.println(status);
            System.out.println(Arrays.asList(headers));
            System.out.println(EntityUtils.toString(entity, "utf-8"));
        });
    }

    public static void testPost() {
        HashMap<String, Object> reqParams = new HashMap<>(8);
        reqParams.put("key1", "val1");
        HashMap<String, String> reqHeaders = new HashMap<>(8);
        reqHeaders.put("key2", "val2");
        // reqHeaders.put("Content-Type", "application/x-www-form-urlencoded");
        HttpUtil.postString("http://127.0.0.1:5000/request", reqHeaders, reqParams, "Hello", (status, headers, entity) -> {
            System.out.println(status);
            System.out.println(Arrays.asList(headers));
            System.out.println(EntityUtils.toString(entity, "utf-8"));
        });
    }

    public static void testPostWWW() {
        HashMap<String, Object> reqParams = new HashMap<>(8);
        reqParams.put("key1", "val1");
        HashMap<String, Object> reqBodyParams = new HashMap<>(8);
        reqBodyParams.put("key3", "val3");
        HashMap<String, String> reqHeaders = new HashMap<>(8);
        reqHeaders.put("key2", "val2");
        // reqHeaders.put("Content-Type", "application/x-www-form-urlencoded");
        HttpUtil.postWWW("http://127.0.0.1:5000/request", reqHeaders, reqParams, reqBodyParams, (status, headers, entity) -> {
            System.out.println(status);
            System.out.println(Arrays.asList(headers));
            System.out.println(EntityUtils.toString(entity, "utf-8"));
        });
    }

    public static void testPostForm() {
        HashMap<String, Object> reqParams = new HashMap<>(8);
        reqParams.put("key1", "val1");
        HashMap<String, Object> reqBodyParams = new HashMap<>(8);
        reqBodyParams.put("key3", "val3");
        HashMap<String, String> reqHeaders = new HashMap<>(8);
        reqHeaders.put("key2", "val2");
        // reqHeaders.put("Content-Type", "application/x-www-form-urlencoded");
        HttpUtil.postForm("http://127.0.0.1:5000/request", reqHeaders, reqParams, reqBodyParams, (status, headers, entity) -> {
            System.out.println(status);
            System.out.println(Arrays.asList(headers));
            System.out.println(EntityUtils.toString(entity, "utf-8"));
        });
    }

    public static void testPostJson() {
        HttpUtil.postJson("http://127.0.0.1:5000/request", null, null, "{\"x\"=123}", (status, headers, entity) -> {
            System.out.println(status);
            System.out.println(Arrays.asList(headers));
            System.out.println(EntityUtils.toString(entity, "utf-8"));
        });
    }

    public static void main(String[] args) {
        testGet();
        testPost();
        testPostWWW();
        testPostForm();
        testPostJson();
    }
}
