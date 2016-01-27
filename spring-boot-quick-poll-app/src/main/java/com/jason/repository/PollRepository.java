package com.jason.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jason.domain.Poll;

/**
 * The Spring Data project aims at addressing this problem by completely eliminating the need to write
 * any repository implementations. With Spring Data, all you need is a repository interface to
 * automatically generate its implementation at runtime. The only requirement is that application
 * repository interfaces should extend one of the many available Spring Data marker interfaces.
 * 
 * PagingAndSortingRepository extends Spring CrudRepository so no worries
 * @author Jason
 *
 */
public interface PollRepository extends PagingAndSortingRepository<Poll, Long> {

}