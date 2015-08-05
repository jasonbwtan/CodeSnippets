package com.jason.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

import com.jason.service.HelloWorld;
import com.jason.service.HelloWorldImpl;

@Configuration
@Profile("live")
public class CacheConfigLive {
	
//	@Bean
//	public CacheManager cacheManager() {
//		log.debug("Cache manager is ehCacheCacheManager");
//		return new EhCacheCacheManager(ehCacheCacheManager().getObject());
//	}

//	@Bean
//	public EhCacheManagerFactoryBean ehCacheCacheManager() {
//		EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
//		cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
//		cmfb.setShared(true);
//		return cmfb;
//	}
	
	@Bean(name="helloBean")
	public HelloWorld newInstance(){
		return new HelloWorldImpl("cachemanager live"+this.getClass());
	}
	
}