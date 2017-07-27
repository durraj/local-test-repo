package com.std.boot.repository;


import org.springframework.data.repository.CrudRepository;

import com.std.boot.model.Role;
import com.std.boot.model.UserProfile;

public interface RoleRepository extends CrudRepository<Role, Long>{

}
