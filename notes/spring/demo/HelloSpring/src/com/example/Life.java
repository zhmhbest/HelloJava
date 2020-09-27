package com.example;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class Life implements BeanPostProcessor {
    public void init() {
        System.out.println("\tLife init.");
    }
    public void destroy() {
        System.out.println("\tLife destroy.");
    }

    /*
     * postProcessBeforeInitialization
     * postProcessAfterInitialization
     * 会为所有Bean都添加处理器执行
     */

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.printf("\tLife %s before init.\n", beanName);
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.printf("\tLife %s after init.\n", beanName);
        return null;
    }
}
