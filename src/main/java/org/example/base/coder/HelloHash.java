package org.example.base.coder;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 用于【消息摘要】或【数字签名】的散列算法
 */
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

    public static void main(String[] args) {
        System.out.println(hashMd5("123"));
        System.out.println(hashSha256("123"));
    }
}
