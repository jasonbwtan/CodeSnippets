package com.jason.integration;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.jason.QuickPollApplication;

/**
 * Integration tests. Starts up a real instance to test poll controller.
 * 
 * @author Jason
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = QuickPollApplication.class)
@WebAppConfiguration
public class PollControllerIT {

	@Inject
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testPostPoll() throws Exception {

		mockMvc.perform(
				post("/v1/polls")
						.param("admin", "false")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.content(
								"{\"question\":\"Who will win SuperBowl this year?\",\"options\":[{\"value\":\"New England Patriots\"},{\"value\":\"Seattle Seahawks\"},{\"value\":\"Green Bay Packers\"},{\"value\":\"Denver Broncos\"}]}"))
				.andExpect(status().is(201)).andExpect(content().string(""));
	}

	/**
	 * status().isOk() --> expect a 200 status code hasSize(20) --> expect collection returns 20 items
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAllPolls() throws Exception {
		mockMvc.perform(get("/v1/polls")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));
	}
}