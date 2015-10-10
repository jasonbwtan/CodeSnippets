package com.jason.spring_autowire_example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import beans.SpellChecker;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Spring Junit test. Test Class loads the beans then is injected into spellChecker field
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = {"classpath*:**/beans.xml"})
public class AppTest 
    extends TestCase
{
	@Autowired
	private SpellChecker spellChecker;

    
    @Test
    public void test1(){
    	spellChecker.checkSpelling();
    }
}
