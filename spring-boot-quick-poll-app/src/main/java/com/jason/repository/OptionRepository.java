package com.jason.repository;
import org.springframework.data.repository.CrudRepository;

import com.jason.domain.Option;

/**
 * The Spring Data project aims at addressing this problem by completely eliminating the need to write
 * any repository implementations. With Spring Data, all you need is a repository interface to
 * automatically generate its implementation at runtime. The only requirement is that application
 * repository interfaces should extend one of the many available Spring Data marker interfaces.
 * 
 * OptionRepository extends Spring Data's CrudRepository and thereby inherits all of its CRUD methods.
 * Because the OptionRepository works with the Option domain object, it passes Option and Long as generic
 * parameter values.
 * 
 * @author Jason
 *
 */
public interface OptionRepository extends CrudRepository<Option, Long> {
}