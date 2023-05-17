package com.hyxc.moikiitos.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.hyxc.moikiitos.domain.Post;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
	
	@Transactional(readOnly=true)
	@Query("select p from Post p where p.blogUser.username in ?1 and p.createdDate > ?2 order by p.createdDate asc")
	List<Post> findByUsernameIn(List<String> usernames, Date createdAfter);
	
	@Transactional(readOnly=true)
	@Query("select p from Post p where p.blogUser.username in ?1 order by p.createdDate desc")
	Page<Post> findByUsernameIn(List<String> usernames, Pageable pageable);
	
}
