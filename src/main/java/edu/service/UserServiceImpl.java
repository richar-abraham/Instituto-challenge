package edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.entity.Constants;
import edu.entity.User;
import edu.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findUserByDNI(String number) {
		return userRepository.findByDni(number);
	}

	@Override
	public void saveUser(User user) {
		user.setNumber(bCryptPasswordEncoder.encode(user.getNumber()));
		user.setActive(Constants.ACTIVE);
		userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return this.userRepository.findAll();
	}

}
