package com.jason.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jason.domain.Vote;

/**
 * The Spring Data project aims at addressing this problem by completely eliminating the need to write
 * any repository implementations. With Spring Data, all you need is a repository interface to
 * automatically generate its implementation at runtime. The only requirement is that application
 * repository interfaces should extend one of the many available Spring Data marker interfaces.
 * 
 * @author Jason
 *
 */
public interface VoteRepository extends CrudRepository<Vote, Long> {
	
	//extend default crudrepo to return a collection of votes by pollId
	@Query(value = "select v.* from Option o, Vote v where o.POLL_ID = ?1 and v.OPTION_ID = o.OPTION_ID", nativeQuery = true)
	public Iterable<Vote> findByPoll(Long pollId);
	
	
}