package edu.service;

import java.util.List;
import java.util.Optional;

import edu.entity.Teacher;

public interface TeacherService {

	public Optional<Teacher> findById(long teacherId);

	public List<Teacher> findAll();

	public void save(Teacher teacher);

	public void delete(long id);

	public List<Teacher> findAllByActive(int active);
}
