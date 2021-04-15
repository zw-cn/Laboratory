package com.zw.springboot.springsource.demo1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class BeanFactoryTest {
    @Test
    public void testSimpleLoad(){
        XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("beanFactoryTest.xml"));
        MyTestBean bean = (MyTestBean) beanFactory.getBean("testBean");
        Assertions.assertEquals(bean.getTestStr(),"testStr");
    }
}
