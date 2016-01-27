package com.jason.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;

import com.jason.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	public User findByUsername(String username);

}