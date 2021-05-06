package org.example.config;

import org.example.IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class HelloProperties {
    public static Properties getProperties(String fileName) {
        try {
            return getProperties(new FileInputStream(fileName));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static Properties getProperties(InputStream in) {
        if(null == in) { return null; }
        try {
            Properties properties = new Properties();
            properties.load(in);
            return properties;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        Properties properties = getProperties(IO.getResourceStream("/demo.properties"));
        System.out.println(properties);
        System.out.println(properties.getProperty("PI"));
    }
}
