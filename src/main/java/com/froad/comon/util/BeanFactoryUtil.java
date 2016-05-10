package com.froad.comon.util;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanFactoryUtil {
    // 单例模式
    private static BeanFactoryUtil util = null;
    private BeanFactory factory = null;

    private BeanFactoryUtil() {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"action-servlet.xml", "applicationContext.xml"});
        factory = (BeanFactory) context;
    }

    // 获取对象工厂
    private static BeanFactory getBeanFactory() {
        if (util == null) {
            util = new BeanFactoryUtil();
        }
        return util.factory;
    }

    // 获取对象
    public static Object getBean(String beanName) {
        return getBeanFactory().getBean(beanName);
    }

}
