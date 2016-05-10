package com.froad.comon.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;


/**
 * 方便取得Spring容器，取得其他服务实例，必须在Spring的配置文件里进行配置 如：<bean id="appUtil"
 * class="com.htsoft.util.core.AppUtil"/> 也提供整个应用程序的相关配置获取方法
 *
 * @author iyooc
 */
@Component("appUtil")
public class AppUtil implements ApplicationContextAware {

    /**
     * 存放应用程序的配置,如邮件服务器等
     */
    private static Map configMap = null;
    /**
     * 应用程序全局对象
     */
    private static ServletContext servletContext = null;

    public static ApplicationContext appContext;

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.appContext = applicationContext;
    }

    /**
     * 取得Bean
     *
     * @param beanId
     * @return
     */
    public static Object getBean(String beanId) {
        return appContext.getBean(beanId);
    }

    /**
     * 取得应用程序的绝对路径
     *
     * @return
     */
    public static String getAppAbsolutePath() {
        return servletContext.getRealPath("/");
    }


    /**
     * 应用程序启动时调用
     *
     * @param in_servletContext
     */
    public static void init(ServletContext in_servletContext) {
        servletContext = in_servletContext;
        configMap = new HashMap();
        // 读取来自config.properties文件的配置,并且放入configMap内,应用程序共同使用
        String filePath = servletContext.getRealPath("/WEB-INF/classes/conf");

        Properties props = new Properties();
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(
                    filePath + "/config.properties"));
            props.load(is);
            Iterator it = props.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                configMap.put(key, props.get(key));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
