package org.example.config;

import net.sf.json.JSONObject;
import org.example.IO;

import java.util.*;

public class MapSlim {
    public static void mapSlim(Map<String, Object> map) {
        List<Object> keys =
        new ArrayList<String>(map.keySet().toArray());
        System.out.println(keys);
        // for (Object key: keys) {
        //     map.remove(key);
            // Object val = map.get(key);
            // if (val instanceof String) {
            //     System.out.println(val.toString());
            //     // 移除空串
            //     if (val.toString().isEmpty()) {
            //         map.remove(key);
            //     }
            // }
            // else if (List.class.isAssignableFrom(val.getClass())) {
            //     System.out.println("List");
            //     // for(Object item: (List)val) {
            //     //     System.out.println(item.toString());
            //     // }
            // }
            // else if (Map.class.isAssignableFrom(val.getClass())) {
            //     System.out.println("Map");
            // }
        // }
    }

    public static void main(String[] args) {
        final String text = IO.readResourceText("/slim.json");
        assert null != text;
        JSONObject json = JSONObject.fromObject(text);
        System.out.println(json);
        mapSlim(json);
        System.out.println(json);
    }
}
