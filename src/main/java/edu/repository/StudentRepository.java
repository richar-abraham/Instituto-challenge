package edu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.entity.Student;
import edu.entity.User;

@Repository("studentRepository")
public interface StudentRepository extends JpaRepository<Student, Long> {

	Optional<Student> findByUser(User user);

	Optional<Student> findByUserAndActive(User user, int active);
}
