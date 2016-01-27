package com.jason.v1.controller;

import java.net.URI;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.jason.domain.Poll;
import com.jason.dto.error.ErrorDetail;
import com.jason.exception.ResourceNotFoundException;
import com.jason.repository.PollRepository;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * http://localhost:8080/polls http://localhost:8080/polls/1
 * 
 * @author Jason
 *
 */
@RestController("pollControllerV1")
@RequestMapping("/v1/")
@Api(value = "polls", description = "Poll API")
public class PollController {

	@Inject
	private PollRepository pollRepository;
	/**
	 * This method conveys the location of the newly created resource in the location header
	 * 
	 * A best practice is to convey the URI to the newly created resource using the Location HTTP header.
	 * Building the URI would require us to inspect the HttpServletRequest object to obtain information
	 * such as Root URI and context. Spring makes the URI generation process easy via its
	 * ServletUriComponentsBuilder utility class e.g
	 * 
	 * URI newPollUri = ServletUriComponentsBuilder...
	 * 
	 * @Valid instructs Spring to perform input validation on input poll object.
	 * 
	 * @param poll
	 * @return
	 */
	@RequestMapping(value = "/polls", method = RequestMethod.POST)
	@ApiOperation(value = "Creates a new Poll", notes = "The newly created poll Id will be sent in the location response header", response = Void.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Poll Created Successfully", response = Void.class),
			@ApiResponse(code = 500, message = "Error creating Poll", response = ErrorDetail.class)})
	public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll) {
		poll = pollRepository.save(poll);

		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(poll.getPollId()).toUri();
		responseHeaders.setLocation(newPollUri);

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/polls", method = RequestMethod.GET)
	@ApiOperation(value = "Retrieves all the polls", response = Poll.class, responseContainer = "List")
	public ResponseEntity<Iterable<Poll>> getAllPolls() {
		Iterable<Poll> allPolls = pollRepository.findAll();
		for (Poll p : allPolls) {
			updatePollResourceWithLinks(p);
		}
		return new ResponseEntity<>(allPolls, HttpStatus.OK);
	}

	@RequestMapping(value = "/polls/{pollId}", method = RequestMethod.GET)
	@ApiOperation(value = "Retrieves a Poll associated with the pollId", response = Poll.class)
	public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
		verifyPoll(pollId);
		Poll p = pollRepository.findOne(pollId);
        updatePollResourceWithLinks(p);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}

	@RequestMapping(value = "/polls/{pollId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
		verifyPoll(pollId);
		pollRepository.save(poll);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/polls/{pollId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
		verifyPoll(pollId);
		pollRepository.delete(pollId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	/**
	 * Exception handling method.
	 * 
	 * @param pollId
	 * @throws ResourceNotFoundException
	 */
	protected void verifyPoll(Long pollId) throws ResourceNotFoundException {
		Poll poll = pollRepository.findOne(pollId);
		if (poll == null) {
			throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
		}
	}
	/**
	 * HATEAOS generate links
	 * @param poll
	 */
	private void updatePollResourceWithLinks(Poll poll) {
		poll.add(linkTo(methodOn(PollController.class).getAllPolls()).slash(poll.getPollId())
				.withSelfRel());
		poll.add(linkTo(methodOn(VoteController.class).getAllVotes(poll.getPollId())).withRel("votes"));
		poll.add(linkTo(methodOn(ComputeResultController.class).computeResult(poll.getPollId()))
				.withRel("compute-result"));
	}
}
