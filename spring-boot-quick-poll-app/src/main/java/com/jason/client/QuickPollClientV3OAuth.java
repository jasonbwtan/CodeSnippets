package com.jason.client;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

import com.jason.domain.Option;
import com.jason.domain.Poll;

public class QuickPollClientV3OAuth {
	private static final String QUICK_POLL_URI_V3 = "http://localhost:8080/oauth2/v3/polls";

	public Poll getPollById(Long pollId) {
		OAuth2RestTemplate restTemplate = restTemplate();
		return restTemplate.getForObject(QUICK_POLL_URI_V3 + "/{pollId}", Poll.class, pollId);
	}

	public URI createPoll(Poll poll) {
		OAuth2RestTemplate restTemplate = restTemplate();
		return restTemplate.postForLocation(QUICK_POLL_URI_V3, poll);
	}

	/**
	 * Spring provides a specialized version of RestTemplate named OAuth2RestTemplate that simplifies
	 * consumption of OAuth2 protected services. The OAuth2RestTemplate requires an instance of
	 * OAuth2ProtectedResourceDetails that contains details of a protected resource such as client id,
	 * client secret, and token URI. Because the QuickPoll API uses a "Password" grant type, we will be
	 * constructing the OAuth2RestTemplate using a ResourceOwnerPasswordResourceDetails instance
	 * 
	 * @return
	 */
	private OAuth2RestTemplate restTemplate() {
		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
		resourceDetails.setGrantType("password");
		resourceDetails.setAccessTokenUri("http://localhost:8080/oauth/token");
		resourceDetails.setClientId("quickpolliOSClient");
		resourceDetails.setClientSecret("top_secret");

		// Set scopes
		List<String> scopes = new ArrayList<>();
		scopes.add("read");
		scopes.add("write");
		resourceDetails.setScope(scopes);

		// Resource Owner details
		resourceDetails.setUsername("mickey");
		resourceDetails.setPassword("cheese");

		return new OAuth2RestTemplate(resourceDetails);
	}

	public static void main(String[] args) {
		QuickPollClientV3OAuth client = new QuickPollClientV3OAuth();
		
		Poll newPoll = new Poll();
		newPoll.setQuestion("What is your favourate color?");
		Set<Option> options = new HashSet<>();
		newPoll.setOptions(options);

		Option option1 = new Option();
		option1.setValue("Red");
		options.add(option1);
		Option option2 = new Option();
		option2.setValue("Blue");
		options.add(option2);

		URI pollLocation = client.createPoll(newPoll);
		System.out.println("Newly Created Poll Location " + pollLocation);
		
		
		client.getPollById(1L);
	}

}