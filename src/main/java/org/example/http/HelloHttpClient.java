package org.example.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HelloHttpClient {
    public static void main(String[] args) throws IOException {
        final String URL = "http://www.baidu.com";
        CloseableHttpClient client = HttpClients.createDefault();
        //
        HttpGet httpGet = new HttpGet(URL);
        //
        CloseableHttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "utf-8");
        System.out.println(result);
        response.close();
    }
}
