package com.std.boot.repository;

import org.springframework.data.repository.CrudRepository;

import com.std.boot.model.UserToken;

public interface UserTokenRepository extends CrudRepository<UserToken, String>{

}
