package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exception.CustomException;
import com.model.User;
import com.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public List<User> getAllUsers(){
		List<User> userList = new ArrayList<>();
		userList = userRepository.findAll();
		return userList;
	}
	
	public User getUser(String username)  {

		return userRepository.findById(username).get();
	}
	
	public void addUser(User user) throws CustomException {
		User user1 = null;
		try {
			 user1 = getUser(user.getUserName());	
		}catch(Exception e) {
			userRepository.save(user);
		}
		
		if(!(user1==null)) {
			throw new CustomException("User already exists with name "+user.getUserName());
		}
		
	}
	
//	public void updateUser(User user) {
//		u
//	}
	
	public void deleteUser(String username) {
		
		userRepository.deleteById(username);
		
	}
}
