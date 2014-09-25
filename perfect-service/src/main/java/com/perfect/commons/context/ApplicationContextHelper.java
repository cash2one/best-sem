package com.perfect.commons.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by vbzer_000 on 2014/9/17.
 */
@Component
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }


    public static Object getBeanByName(String name) {
        return context.getBean(name);
    }

    public static Object getBeanByClass(Class<?> clz) {
        return context.getBean(clz);
    }
}
