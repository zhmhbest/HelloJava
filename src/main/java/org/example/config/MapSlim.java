package org.example.config;

import net.sf.json.JSONObject;
import java.util.*;
import org.example.IO;

public class MapSlim {
    @SuppressWarnings("unchecked")
    public static void eliminateKeyByEmptyValue(Map<String, Object> map) {
        for (Object rawKey : map.keySet().toArray()) {
            String key = rawKey.toString();
            Object val = map.get(key);
            if (val instanceof String) {
                // 移除空串
                if (val.toString().isEmpty()) {
                    map.remove(key);
                }
            } else if (List.class.isAssignableFrom(val.getClass())) {
                List<Object> subList = (List<Object>) val;
                for (Object item : subList) {
                    if (Map.class.isAssignableFrom(item.getClass())) {
                        eliminateKeyByEmptyValue((Map<String, Object>) item);
                    }
                }
            } else if (Map.class.isAssignableFrom(val.getClass())) {
                eliminateKeyByEmptyValue((Map<String, Object>) val);
            }
        }
    }

    // public static void sortMapByKey(Map<String, Object> map) {
    //
    // }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        final String text = IO.readResourceText("/slim.json");
        assert null != text;
        JSONObject json = JSONObject.fromObject(text);
        System.out.println(json);
        eliminateKeyByEmptyValue(json);
        System.out.println(json);
    }
}
