package com.hyxc.moikiitos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.hyxc.moikiitos.domain.BlogUser;

public interface BlogUserRepository extends PagingAndSortingRepository<BlogUser, Long> {
	BlogUser findByUsername(String username);
	
	@Transactional(readOnly=true)
	@Query("select u from BlogUser u where u.username like ?1 or u.email like ?1")
	List<BlogUser> findByUsernameLike(String username);
}
