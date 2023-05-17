
package com.hyxc.moikiitos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.hyxc.moikiitos.domain.BlogUser;
import com.hyxc.moikiitos.domain.Follower;

public interface FollowerRepository extends PagingAndSortingRepository<Follower, Long> {
	@Query("select f.followerKey.target.username from Follower f where f.followerKey.follower.username = ?1 order by f.followerKey.target.username")
	List<String> findByFollowerUsername(String username);

	@Query("select f.followerKey.target from Follower f where f.followerKey.follower.username = ?1 order by f.followerKey.target.username")
	List<BlogUser> findUserByFollowerUsername(String username);
	
	@Query("select f.followerKey.follower from Follower f where f.followerKey.target.username = ?1 order by f.followerKey.follower.username")
	List<BlogUser> findUserByTargetUsername(String username);


}
