package com.jason.log4j2_example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App {
	static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		logger.info("Hessy!");
		logger.info("Hesasdasdsy!");
		logger.debug("yoyoyoo");
		logger.debug("123");
		logger.info("456");

	}
}
