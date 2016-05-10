package com.froad.comon;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.util.StatusPrinter;
import com.froad.comon.util.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;
import java.util.Properties;

/**
 * Created by zhou on 2015/4/28.
 */
public class InitLogBackContext  extends PropertyPlaceholderConfigurer {

    private static Logger logger = Logger.getLogger(InitLogBackContext.class);
    private String logBackXmlPath;

    /**
     * load logback.xml file
     */
    public void loadLogBackXml() {

        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

        StatusPrinter.print(lc);

        JoranConfigurator configurator = new JoranConfigurator();

        String logBackXmlPath =  this.logBackXmlPath;
//        String logBackXmlPath = System.getProperty("RootPath") + "/" + this.logBackXmlPath;
        File logBackFile = new File(logBackXmlPath);

        if (!logBackFile.exists()) {
            System.out.println("NoSuchFile:" + logBackXmlPath);
            System.exit(-1);
        }
        configurator.setContext(lc);
        lc.reset();

        try {
            configurator.doConfigure(logBackFile);
            StatusPrinter.print(lc);

            lc.start();
            logger.info("Load OK :" + logBackXmlPath);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param beanFactoryToProcess
     * @param props
     * @throws BeansException
     */
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            System.setProperty(keyStr,value);
        }

        loadLogBackXml();
    }

    public String getLogBackXmlPath() {
        return logBackXmlPath;
    }

    public void setLogBackXmlPath(String logBackXmlPath) {
        this.logBackXmlPath = logBackXmlPath;
    }
}
