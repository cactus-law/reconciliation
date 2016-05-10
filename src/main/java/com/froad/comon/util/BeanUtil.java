package com.froad.comon.util;

import com.froad.comon.util.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;

public class BeanUtil {
    private final static Logger logger = Logger.getLogger(BeanUtil.class);

    public static Object reflectSetValue(String className, Map<String, Object> fiedsData) throws Exception {
        //获取对象class
        Class<?> tempC = Class.forName(className);
        //获取对象默认构造方法
        Constructor<?> constructor = tempC.getConstructor();
        //默认构造方法创建对象
        Object instance = constructor.newInstance();
        //获取对象所有set方法
        Method[] methods = tempC.getMethods();
        //执行set方法赋值
        for (Method method : methods) {
            if (method.getName().startsWith("set")) {
                String endWithMethodName = method.getName().toLowerCase().substring(3, 4) + method.getName().substring(4, method.getName().length());
                if (fiedsData.containsKey(endWithMethodName)) {
                    method.invoke(instance, fiedsData.get(endWithMethodName));
                }
            }
        }
        return instance;
    }
}
