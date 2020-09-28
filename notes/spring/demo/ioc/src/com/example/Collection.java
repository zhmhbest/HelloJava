package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Collection {
    protected Object[] obj;
    protected String[] arr;
    protected List<String> list;
    protected Map<String, String> map;
    protected Set<String> set;

    @Override
    public String toString() {
        return String.format(
                "Collection{obj=%s, arr=%s, list=%s, map=%s, set=%s}",
                Arrays.toString(this.obj),
                Arrays.toString(this.arr),
                this.list,
                this.map,
                this.set
        );
    }

    public Object[] getObj() {
        return obj;
    }

    public void setObj(Object[] obj) {
        this.obj = obj;
    }

    public String[] getArr() {
        return arr;
    }

    public void setArr(String[] arr) {
        this.arr = arr;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }
}
