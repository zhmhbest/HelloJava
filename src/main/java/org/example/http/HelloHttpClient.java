package org.example.http;

import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import static org.example.http.HttpUtil.doGet;
import static org.example.http.HttpUtil.doPost;

public class HelloHttpClient {
    public static void testGet() {
        HashMap<String, Object> reqParams = new HashMap<>(8);
        reqParams.put("key1", "val1");
        HashMap<String, String> reqHeaders = new HashMap<>(8);
        reqHeaders.put("key2", "val2");

        doGet("http://127.0.0.1:5000/request", reqHeaders, reqParams, (status, headers, entity) -> {
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

        doPost("http://127.0.0.1:5000/request", reqHeaders, reqParams, new StringEntity("x=123&y=456", "utf-8"), (status, headers, entity) -> {
            System.out.println(status);
            System.out.println(Arrays.asList(headers));
            System.out.println(EntityUtils.toString(entity, "utf-8"));
        });
    }

    public static void main(String[] args) {
        testGet();
        testPost();
    }
}
