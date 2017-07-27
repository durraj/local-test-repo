package com.std.account.service;

import com.std.boot.model.User;
import com.std.boot.model.UserProfile;

public interface UserService {
	User save(User user);

    User findByEmail(String email);

	User getUserByEmail(String email);
}
