package com.jason.json_examples;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class JsonExamples {
	public static void main(String[] args) {
		JSONObject response = new JSONObject();
		
		/*Json array from ArrayList */
		List<String> list1 = Arrays.asList("1","2","3");
		response.put("example1",JSONArray.fromObject(list1) );
		out.println(response);
		
		/*Json array from javabean */
		Bean bean = new Bean();
		response.put("example2", JSONObject.fromObject(bean));
		out.println(response);
	}
}
