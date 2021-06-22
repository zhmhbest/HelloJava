package org.example.base.coder;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class HelloTranscoding {
    public static String encodeBase64(String message) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(message.getBytes());
    }

    public static String decodeBase64(String message) {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes;
        try {
            bytes = decoder.decodeBuffer(message);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return new String(bytes);
    }

    public static String encodeUrl(String message) {
        try {
            return URLEncoder.encode(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decodeUrl(String message) {
        try {
            return URLDecoder.decode(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String url = encodeUrl("https://cn.bing.com/");
        System.out.println(url);
        System.out.println(decodeUrl(url));

        String base64 = encodeBase64("123");
        System.out.println(base64);
        System.out.println(decodeBase64(base64));
    }
}
