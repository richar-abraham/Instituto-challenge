package edu.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.entity.Role;
import edu.repository.RoleRepository;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public Optional<Role> findByName(String name) {
		return roleRepository.findByRole(name);
	}

}
