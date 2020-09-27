package com.example;

import java.lang.String;
import org.springframework.beans.factory.FactoryBean;

public class Factory implements FactoryBean<String> {

    @Override
    public String getObject() throws Exception {
        return new String("FactoryBean");
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
