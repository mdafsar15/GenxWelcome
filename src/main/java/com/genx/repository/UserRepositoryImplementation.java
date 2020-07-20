package com.genx.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.genx.model.User;


@Repository
@Transactional
public interface UserRepositoryImplementation extends JpaRepository<User, Long> {
	
	@Modifying
	@Query(value = "update user set is_verified = true where id = ?", nativeQuery = true)
	public void verify(boolean id);
}
