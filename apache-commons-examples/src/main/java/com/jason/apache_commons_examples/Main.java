package com.jason.apache_commons_examples;

import java.text.ParseException;

import org.apache.commons.lang3.time.DateUtils;

public class Main 
{
    public static void main( String[] args ) throws Exception
    {
    	//Parse a set of valid date inputs
    	String[] acceptedFormats = {"dd/MM/yyyy","dd/MM/yyyy HH:mm","dd/MM/yyyy HH:mm:ss"};
		System.out.println(DateUtils.parseDate("12/07/2012", acceptedFormats));
    }
}
