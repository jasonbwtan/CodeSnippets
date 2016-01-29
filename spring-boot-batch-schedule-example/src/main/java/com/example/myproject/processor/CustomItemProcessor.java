package com.example.myproject.processor;

import org.springframework.batch.item.ItemProcessor;

import com.example.myproject.entities.CustomPojo;
import com.example.myproject.entities.InputRecord;

public class CustomItemProcessor implements
		ItemProcessor<InputRecord, CustomPojo> {

	@Override
	public CustomPojo process(final InputRecord pojo) throws Exception {
		final String id = encode(pojo.getId());
		final String desc = encode(pojo.getDescription());

		CustomPojo encodedPojo = new CustomPojo(id, desc);
		encodedPojo.setAddedField("added data from processor");
		return encodedPojo;

	}

	private String encode(String word) {
		StringBuffer str = new StringBuffer(word);
		return str.reverse().toString();
	}

}