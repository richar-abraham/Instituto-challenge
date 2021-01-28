package edu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.entity.Subject;

@Repository("subjectRepository")
public interface SubjectRepository extends JpaRepository<Subject, Long> {

	List<Subject> findAllByActive(int active);

}
