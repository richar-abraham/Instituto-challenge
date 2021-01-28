package edu.service;

import java.util.Optional;

import edu.entity.Role;

public interface RoleService {

	public Optional<Role> findByName(String name);
}
