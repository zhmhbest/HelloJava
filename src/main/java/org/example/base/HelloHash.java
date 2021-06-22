package org.example.base;


import org.apache.commons.codec.binary.Hex;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HelloHash {

    public static String hashMd5(String message) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] ciphertext = messageDigest.digest(message.getBytes());
        return Hex.encodeHexString(ciphertext);
    }

    public static String hashSha256(String message) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] ciphertext = messageDigest.digest(message.getBytes());
        return Hex.encodeHexString(ciphertext);
    }

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
        System.out.println(hashMd5("123"));
        System.out.println(hashSha256("123"));

        String url = encodeUrl("https://cn.bing.com/");
        System.out.println(url);
        System.out.println(decodeUrl(url));

        String base64 = encodeBase64("123");
        System.out.println(base64);
        System.out.println(decodeBase64(base64));
    }
}
