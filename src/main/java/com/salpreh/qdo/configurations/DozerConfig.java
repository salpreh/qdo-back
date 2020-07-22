package com.salpreh.qdo.configurations;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerConfig {
	
@Bean(name = "org.dozer.Mapper")
public DozerBeanMapper dozerBean() {
	DozerBeanMapper dozerBean = new DozerBeanMapper();
	
	return dozerBean;
}

}
