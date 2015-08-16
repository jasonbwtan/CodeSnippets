package com.jason.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class JerseyClient {
public static void main(String[] args) {
	Client client = Client.create();

	WebResource webResource = client
			.resource("https://google.com");
}
}
