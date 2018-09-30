package com.alexb.springboot11.util;

import net.ttddyy.dsproxy.asserts.ProxyTestDataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DataSourceBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DataSource) {
            return new ProxyTestDataSource((DataSource) bean);
        }
        return bean;
    }
}