package org.powerstone.acegi.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AcegiCacheManagerFactoryBean implements FactoryBean, ApplicationContextAware {
    private ApplicationContext applicationContext;

    protected final Log logger = LogFactory.getLog(getClass());

    String targetBeanName;

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public Object getObject() throws Exception {
        return applicationContext.getBean(targetBeanName);
    }

    public Class getObjectType() {
        return AcegiCacheManager.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void setTargetBeanName(String targetBeanName) {
        this.targetBeanName = targetBeanName;
    }

}
