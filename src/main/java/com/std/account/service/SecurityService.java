package com.std.account.service;

public interface SecurityService {
	String findLoggedInUsername();

    void autologin(String email, String password);
}
