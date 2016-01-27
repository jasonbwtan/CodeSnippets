package com.jason.v2.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jason.domain.Vote;
import com.jason.dto.OptionCount;
import com.jason.dto.VoteResult;
import com.jason.repository.VoteRepository;

/**
 * Because we don't have any domain objects that can directly help generate this resource representation,
 * we need two Data Transfer Objects(DTO's), OptionCount and VoteResult
 * 
 * @author Jason
 *
 */
@RestController ("computeResultControllerV2")
@RequestMapping ("/v2/")
public class ComputeResultController {

	@Inject
	private VoteRepository voteRepository;

	@RequestMapping(value = "/computeresult", method = RequestMethod.GET)
	public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
		VoteResult voteResult = new VoteResult();
		Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);
		
		// Algorithm to count votes
		int totalVotes = 0;
		Map<Long, OptionCount> tempMap = new HashMap<Long, OptionCount>();
		for(Vote v : allVotes) {
		        totalVotes ++;
		        // Get the OptionCount corresponding to this Option
		        OptionCount optionCount = tempMap.get(v.getOption().getId());
		        if(optionCount == null) {
		                optionCount = new OptionCount();
		                optionCount.setOptionId(v.getOption().getId());
		                tempMap.put(v.getOption().getId(), optionCount);
		        }
		        optionCount.setCount(optionCount.getCount()+1);
		}
		voteResult.setTotalVotes(totalVotes);
		voteResult.setResults(tempMap.values());
		
		// response is structured based on the fields of voteResult object
		return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
	}
}