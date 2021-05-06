package org.example.config;

import com.opencsv.CSVReader;
import org.example.IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;


public class HelloCSV {
    public static void main(String[] args) throws IOException {
        InputStream is = IO.getResourceStream("/demo.csv");
        CSVReader reader = new CSVReader(
                new BufferedReader(new InputStreamReader(is)),
                ',', '"', '\\'
        );
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            System.out.println(Arrays.toString(nextLine));
        }
    }
}
