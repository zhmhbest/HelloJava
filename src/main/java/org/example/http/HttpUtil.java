package org.example.http;

import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpUtil {
    static {
        // 关闭日志
        System.setProperty("org.apache.commons.logging.LogFactory", "org.apache.commons.logging.impl.LogFactoryImpl");
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.simplelog.defaultlog", "error");
    }

    public interface ResponseCallback {
        void run(StatusLine status, Header[] headers, HttpEntity entity) throws IOException;
    }

    public static URI getParameterizedUri(String url, Map<String, Object> requestLineParameters) {
        URI uri = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            if (null != requestLineParameters) {
                for (Map.Entry<String, Object> item : requestLineParameters.entrySet()) {
                    builder.setParameter(item.getKey(), item.getValue().toString());
                }
            }
            uri = builder.build();
        } catch (URISyntaxException e) {
            System.err.println(e.getMessage());
        }
        return uri;
    }

    public static void setHeaders(HttpRequestBase request, Map<String, String> requestHeaders) {
        if (null != requestHeaders) {
            for (Map.Entry<String, String> item : requestHeaders.entrySet()) {
                request.setHeader(item.getKey(), item.getValue());
            }
        }
    }

    public static void request(CloseableHttpClient client, HttpRequestBase request, ResponseCallback callback) {
        CloseableHttpResponse response = null;
        try {
            response = client.execute(request);
            StatusLine status = response.getStatusLine();
            Header[] headers = response.getAllHeaders();
            HttpEntity entity = response.getEntity();
            callback.run(status, headers, entity);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void get(
            String url,
            Map<String, String> requestHeaders,
            Map<String, Object> requestLineParameters,
            ResponseCallback callback
    ) {
        CloseableHttpClient client = HttpClients.createDefault();
        URI uri = getParameterizedUri(url, requestLineParameters);
        if (null == uri) { return; }
        HttpGet request = new HttpGet(uri);
        setHeaders(request, requestHeaders);
        request(client, request, callback);
    }

    public static void post(
            String url,
            Map<String, String> requestHeaders,
            Map<String, Object> requestLineParameters,
            HttpEntity entity,
            ResponseCallback callback
    ) {
        CloseableHttpClient client = HttpClients.createDefault();
        URI uri = getParameterizedUri(url, requestLineParameters);
        if (null == uri) { return; }
        HttpPost request = new HttpPost(uri);
        request.setEntity(entity);
        setHeaders(request, requestHeaders);
        request(client, request, callback);
    }

    public static void postString(
            String url,
            Map<String, String> requestHeaders,
            Map<String, Object> requestLineParameters,
            String content,
            ResponseCallback callback
    ) {
        post(url, requestHeaders, requestLineParameters, new StringEntity(content, "utf-8"), callback);
    }

    public static void postJson(
            String url,
            Map<String, String> requestHeaders,
            Map<String, Object> requestLineParameters,
            String content,
            ResponseCallback callback
    ) {
        if (null == requestHeaders) {
            requestHeaders = new HashMap<>(1);
        }
        requestHeaders.put("Content-Type", "application/json");
        post(url, requestHeaders, requestLineParameters, new StringEntity(content, "utf-8"), callback);
    }

    public static void postWWW(
            String url,
            Map<String, String> requestHeaders,
            Map<String, Object> requestLineParameters,
            Map<String, Object> requestBodyParameters,
            ResponseCallback callback
    ) {
        // application/x-www-form-urlencoded
        List<NameValuePair> dumpPair = new ArrayList<>();
        for (Map.Entry<String, Object> item : requestBodyParameters.entrySet()) {
            dumpPair.add(new BasicNameValuePair(item.getKey(), item.getValue().toString()));
        }
        try {
            post(url, requestHeaders, requestLineParameters, new UrlEncodedFormEntity(dumpPair, "utf-8"), callback);
        } catch (UnsupportedEncodingException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void postForm(
            String url,
            Map<String, String> requestHeaders,
            Map<String, Object> requestLineParameters,
            Map<String, Object> requestBodyParameters,
            ResponseCallback callback
    ) {
        // multipart/form-data
        MultipartEntityBuilder formBuilder = MultipartEntityBuilder.create();
        for (Map.Entry<String, Object> item : requestBodyParameters.entrySet()) {
            formBuilder.addTextBody(item.getKey(), item.getValue().toString());
        }
        post(url, requestHeaders, requestLineParameters, formBuilder.build(), callback);
    }
}
