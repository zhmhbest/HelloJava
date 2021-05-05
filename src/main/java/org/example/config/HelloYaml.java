package org.example.config;

import org.example.IO;
import org.yaml.snakeyaml.Yaml;
import java.util.List;
import java.util.Map;


public class HelloYaml {
    public static void main(String[] args) {
        //读yaml文件
        final String text = IO.readResourceText("/demo.yaml");
        Yaml yaml = new Yaml();
        //获取对象
        Map<String, List<Map<String, Object>>> obj = yaml.load(text);
        //获取列表
        List<Map<String, Object>> list = obj.get("depart");
        //获取列表中第一个对象 {}内为对象，[]内为列表
        Map<String, Object> one = list.get(0);

        System.out.println(obj);
        System.out.println(list);
        System.out.println(one);
        System.out.println(one.get("id"));
        System.out.println(one.get("name"));
        System.out.println(one.get("salary"));
    }
}
