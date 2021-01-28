package edu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.entity.Enrollment;
import edu.entity.Student;
import edu.entity.Subject;

@Repository("enrollmentRepository")
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

	List<Enrollment> findByStudent(Student student);

	List<Enrollment> findByStudent(Subject subject);
}
