package edu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.entity.Subject;
import edu.repository.SubjectRepository;

@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	public List<Subject> findAll() {
		Sort sort = new Sort(Sort.Direction.ASC, "name");
		return subjectRepository.findAll(sort);
	}

	@Override
	public void save(Subject subject) {
		subjectRepository.save(subject);
	}

	@Override
	public void deleteById(long subjectId) {
		subjectRepository.deleteById(subjectId);
	}

	@Override
	public Optional<Subject> findById(long id) {

		return subjectRepository.findById(id);
	}

	@Override
	public List<Subject> findAllByActive(int active) {

		return subjectRepository.findAllByActive(active);
	}

}
