package com.jason.json_examples;

import java.util.Arrays;
import java.util.List;

public class Bean {
	String a = "poo";
	int b = 1;
	List<String> c = Arrays.asList("1", "2", "3");

	public String getA() {
		return a;
	}

	public int getB() {
		return b;
	}

	public Object getC() {
		return c;
	}

}