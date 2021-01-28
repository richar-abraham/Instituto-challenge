package edu.service;

import java.util.List;
import java.util.Optional;

import edu.entity.Student;
import edu.entity.User;

public interface StudentService {
	public List<Student> findAll();

	public void save(Student student);

	public void deleteById(long id);

	public Optional<Student> findByUser(User user, int active);
}
