package edu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.entity.Teacher;
import edu.repository.TeacherRepository;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;

	public Optional<Teacher> findById(long teacherId) {
		return this.teacherRepository.findById(teacherId);
	}

	public List<Teacher> findAll() {
		return this.teacherRepository.findAll();
	}

	@Override
	public void save(Teacher teacher) {
		this.teacherRepository.save(teacher);
	}

	@Override
	public void delete(long id) {
		teacherRepository.deleteById(id);
	}

	@Override
	public List<Teacher> findAllByActive(int active) {
		return teacherRepository.findAllByActive(active);
	}

}
