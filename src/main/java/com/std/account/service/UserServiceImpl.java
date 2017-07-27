package com.std.account.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.std.boot.model.Role;
import com.std.boot.model.User;
import com.std.boot.model.UserProfile;
import com.std.boot.repository.RoleRepository;
import com.std.boot.repository.UserProfileRepository;
import com.std.boot.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	 	/*@Autowired
	    private UserProfileRepository userRepository;*/
	    @Autowired
	    private RoleRepository roleRepository;
	    @Autowired
	    private UserRepository userRepository;
	    /*@Autowired
	    private BCryptPasswordEncoder bCryptPasswordEncoder;*/

	    /*@Override
	    public void save(UserProfile user) {
	        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	        user.setPassword(user.getPassword());
	    	Iterable<Role> roles = roleRepository.findAll();
	        Iterator<Role> rolesItr = roles.iterator();
	        Set rolesSet = new HashSet<>();
	        while(rolesItr.hasNext())
	        {
	        	rolesSet.add(rolesItr.next());
	        }
	        user.setRoles(new HashSet<>(rolesSet));
	        userRepository.save(user);
	    }*/
	    @Override
	    public User findByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }
		@Override
		public User getUserByEmail(String email) {
			return userRepository.findByEmail(email);
		}
		@Override
		public User save(User user) {
			return userRepository.save(user);
		}
		
}
