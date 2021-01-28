package edu.service;

import java.util.List;

import edu.entity.User;

public interface UserService {
	public List<User> findAll();

	public User findUserByDNI(String dni);

	public void saveUser(User user);
}
