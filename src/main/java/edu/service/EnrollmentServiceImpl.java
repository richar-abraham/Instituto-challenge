package edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.entity.Constants;
import edu.entity.Enrollment;
import edu.entity.Student;
import edu.entity.Subject;
import edu.repository.EnrollmentRepository;

@Service("enrollmentService")
public class EnrollmentServiceImpl implements EnrollmentService {

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	@Override
	public List<Enrollment> findByStudent(Student student) {
		return enrollmentRepository.findByStudent(student);
	}

	@Override
	public List<Enrollment> findBySubject() {
		Subject subject = null;
		return enrollmentRepository.findByStudent(subject);
	}

	@Override
	public void save(Enrollment enrollment) {
		enrollmentRepository.save(enrollment);

	}

	@Override
	public List<Enrollment> findAll() {
		return enrollmentRepository.findAll();
	}

	@Override
	public void deleteById(long enrollId) {
		enrollmentRepository.deleteById(enrollId);

	}

	@Override
	public void save(Student student, Subject subjectSelected) {
		Enrollment enrollment = new Enrollment(Constants.ACTIVE, student, subjectSelected);
		enrollmentRepository.save(enrollment);

	}

}
