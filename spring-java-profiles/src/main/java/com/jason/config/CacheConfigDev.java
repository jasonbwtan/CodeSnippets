package com.jason.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jason.service.HelloWorld;
import com.jason.service.HelloWorldImpl;

@Configuration
@Profile("dev")
public class CacheConfigDev {

	
//	@Bean
//    public CacheManager concurrentMapCacheManager() {
//		log.debug("Cache manager is concurrentMapCacheManager");
//        return new ConcurrentMapCacheManager("movieFindCache");
//    }
	
	@Bean(name="helloBean")
	public HelloWorld newInstance(){
		return new HelloWorldImpl("cachemanager dev"+this.getClass());
	}
	
}