package org.example.http;

import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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

    public static void doRequest(CloseableHttpClient client, HttpRequestBase request, ResponseCallback callback) {
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

    public static void doGet(
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
        doRequest(client, request, callback);
    }

    public static void doPost(
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
        doRequest(client, request, callback);
    }
}
