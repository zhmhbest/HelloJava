package org.example.config;

import com.electronwill.toml.Toml;
import org.example.IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class HelloToml {
    public static void main(String[] args) throws IOException {
        InputStream is = IO.getResourceStream("/demo.toml");

        Map<String, Object> result = Toml.read(is);
        System.out.println(result);
    }
}
