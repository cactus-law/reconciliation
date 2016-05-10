package com.froad.comon.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author zhoujunliang
 *         开放给程序使用Spring的properties配置文件的入口
 */
public class PropertyUtil extends PropertyPlaceholderConfigurer implements Serializable {
    private static final long serialVersionUID = 2786523942878411811L;
    private static Map<String, String> constantParams = new HashMap<String, String>();


    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            constantParams.put(keyStr, value);
        }
    }

    /**
     * 获取配置文件Key对应的值[不存在的时候，返回null]
     *
     * @param key
     */
    public static String getProperties(String key) {
        return constantParams.get(key);
    }


    public static void main(String[] args) {
        System.out.println(PropertyUtil.getProperties("jdbc.url"));
    }
}
