package ioc;

import org.springframework.beans.factory.FactoryBean;

public class demoFactory implements FactoryBean<String>  {
    @Override
    public String getObject() throws Exception {
        return new String("FactoryBean");
    }
    @Override
    public Class<?> getObjectType() { return null; }
    @Override
    public boolean isSingleton() { return false; }

    public static void main(String[] args) {
        TestBeanConfig.loadXML(demoFactory.class);
    }
}
