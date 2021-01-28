package edu.service;

import java.util.List;

import edu.entity.Enrollment;
import edu.entity.Student;
import edu.entity.Subject;

public interface EnrollmentService {

	public List<Enrollment> findBySubject();

	public void save(Enrollment enrollment);

	public List<Enrollment> findByStudent(Student student);

	public List<Enrollment> findAll();

	public void deleteById(long enrollId);

	public void save(Student student, Subject subjectSelected);

}
