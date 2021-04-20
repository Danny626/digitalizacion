package com.albo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Component
public class DigitalizacionInterceptorConfig extends WebMvcConfigurationSupport {
	
	@Autowired
	private DigitalizacionInterceptor transitoInterceptor;
	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(transitoInterceptor);
	}
}
