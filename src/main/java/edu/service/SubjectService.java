package edu.service;

import java.util.List;
import java.util.Optional;

import edu.entity.Subject;

public interface SubjectService {
	public List<Subject> findAll();

	public void save(Subject subject);

	public void deleteById(long subjectId);

	public Optional<Subject> findById(long id);

	public List<Subject> findAllByActive(int active);
}
