package com.froad.comon;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

public class BeanFactoryHelper implements org.springframework.beans.factory.BeanFactoryAware {

    //定义BeanFactory为类静态变量
    private static BeanFactory factory;


    //因为实现了BeanFactoryAware,所以只要类BeanFactoryHelper归spring容器管理,
    //当创建spring容器时,就会自动调用setBeanFactory方法,注入beanFactory


    //定义为类静态方法
    public static BeanFactory getBeanFactory() {
        return factory;
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        BeanFactoryHelper.factory = beanFactory;
    }
}
