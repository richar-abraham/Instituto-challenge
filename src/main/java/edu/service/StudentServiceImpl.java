package edu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.entity.Student;
import edu.entity.User;
import edu.repository.StudentRepository;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	@Override
	public void save(Student student) {
		studentRepository.save(student);

	}

	@Override
	public void deleteById(long id) {

		studentRepository.deleteById(id);
	}

	@Override
	public Optional<Student> findByUser(User user, int active) {
		return studentRepository.findByUserAndActive(user, active);
	}

}
