package edu.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.entity.Constants;
import edu.entity.Enrollment;
import edu.entity.Student;
import edu.entity.Subject;
import edu.entity.Teacher;
import edu.entity.User;
import edu.form.SubjectForm;
import edu.service.EnrollmentService;
import edu.service.StudentService;
import edu.service.SubjectService;
import edu.service.TeacherService;
import edu.service.UserService;
import edu.util.CommonsUtil;

@Controller
public class SubjectController {

	@Autowired
	private UserService userService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private EnrollmentService enrollmentService;

	@Autowired
	public SubjectController() {
	}

	@GetMapping("/deleteSubject")
	public String deleteSubject(Model model, @RequestParam long subjectId) {

		subjectService.deleteById(subjectId);
		model.addAttribute("message", "Se eliminó la subjecta correctamente.");
		return "confirmation";
	}

	@GetMapping("/enrollment/delete")
	public String deleteEnrollment(Model model, @RequestParam long id) {

		enrollmentService.deleteById(id);
		model.addAttribute("message", "Se eliminó la subjecta correctamente.");
		return "confirmation";
	}

	@GetMapping("/subject")
	public String subjects(Model model, HttpServletRequest request) {
		CommonsUtil.setUserRole(model);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) auth
				.getPrincipal();
		List<Subject> subjects = null;
		if (CommonsUtil.isAdmin(principal)) {
			subjects = subjectService.findAll();
		} else {
			subjects = subjectService.findAllByActive(Constants.ACTIVE);
		}
		List<Teacher> teachersList = this.teacherService.findAllByActive(Constants.ACTIVE);
		SubjectForm subjectForm = new SubjectForm();
		model.addAttribute("subjectForm", subjectForm);
		model.addAttribute("teachersList", teachersList);
		model.addAttribute("subjects", subjects);
		String templateHome = "subject";
		return templateHome;
	}

	@PostMapping("/subject")
	public String subjectsPost(Model model, @Valid SubjectForm form, BindingResult bindingResult) {
		CommonsUtil.setUserRole(model);
		if (bindingResult.hasErrors()) {
//			return "subject :: #nuevaEntidadModal";
		}
		Optional<Teacher> teacher = this.teacherService.findById(form.getTeacherId());
		if (teacher.isPresent()) {
			form.setTeacher(teacher.get());
			String[] time = form.getTime().split(":");
			try {
				Subject subject = new Subject(form);
				subjectService.save(subject);
				model.addAttribute("message", "Se registró la entidad con éxito");
			} catch (Exception e) {
				model.addAttribute("message", "No se pudo realizar la operación. Verifique los datos");
			}

		} else {
			model.addAttribute("message", "No se encontró el profesor con Id: " + form.getTeacherId());
		}
		return "confirmation";
	}

	@GetMapping("/subject/delete")
	public String subjectDelete(Model model, @RequestParam("id") long id) {
		CommonsUtil.setUserRole(model);
		subjectService.deleteById(id);
		model.addAttribute("message", "La operación se realizo exitosamente");
		return "confirmation";
	}

	@GetMapping("/enrollments")
	public String enrollments(Model model) {
		CommonsUtil.setUserRole(model);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByDNI(auth.getName());
		org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) auth
				.getPrincipal();
		if (CommonsUtil.isAdmin(principal)) {
			List<Enrollment> subList = this.enrollmentService.findAll();
			model.addAttribute("enrollments", subList);

		} else {

			Optional<Student> student = studentService.findByUser(user, Constants.ACTIVE);
			if (student.isPresent()) {
				List<Enrollment> subList = this.enrollmentService.findByStudent(student.get());
				model.addAttribute("enrollments", subList);
			}
		}
		return "enrollments";
	}

	@GetMapping("/subject/enroll")
	public String subjectEnroll(Model model, @RequestParam("id") long id) {
		CommonsUtil.setUserRole(model);
		Optional<Subject> subject = subjectService.findById(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByDNI(auth.getName());
		Optional<Student> student = studentService.findByUser(user, Constants.ACTIVE);

		if (subject.isPresent() && student.isPresent()) {
			List<Enrollment> previousEnrollments = this.enrollmentService.findByStudent(student.get());
			Subject subjectSelected = subject.get();

			Optional<Enrollment> overlapedSubject = previousEnrollments.stream()
					.filter(e -> e.getSubject().getTime().equals(subjectSelected.getTime())).findAny();

			if (overlapedSubject.isPresent()) {
				model.addAttribute("message", "La operación no se pudo realizar. Ya existe inscripción en ese horario");
			} else if (subjectSelected.getMax() == previousEnrollments.size()) {
				model.addAttribute("message", "La operación no se pudo realizar. No hay cupo en esa materia");
			} else {

				enrollmentService.save(student.get(), subjectSelected);
			}

			model.addAttribute("message", "La operación se realizo exitosamente");
		} else {

			model.addAttribute("message", "La operación no se pudo realizar. Materia o alumno no estan disponibles");
		}
		return "confirmation";
	}

}
