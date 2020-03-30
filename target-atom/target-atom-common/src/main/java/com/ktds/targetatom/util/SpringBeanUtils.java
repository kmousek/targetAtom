package com.ktds.targetatom.util;

import org.springframework.context.ApplicationContext;

import com.ktds.targetatom.common.ApplicationContextProvider;

public class SpringBeanUtils {
	public static Object getBean(String beanName) {
		ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
		
		return applicationContext.getBean(beanName);
	}
}
