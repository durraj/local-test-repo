package com.std.boot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.std.boot.model.UserProfile;

public interface UserProfileRepository extends CrudRepository<UserProfile, Long>{
	public UserProfile findByEmail(String email);

}
