package edu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.entity.Teacher;

@Repository("teacherRepository")
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	List<Teacher> findAllByActive(int active);
}
