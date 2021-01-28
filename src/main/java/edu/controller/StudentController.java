package edu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.entity.Student;
import edu.entity.User;
import edu.service.StudentService;
import edu.service.UserService;
import edu.util.CommonsUtil;

@Controller
public class StudentController {

	@Autowired
	private UserService userService;
	@Autowired
	private StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/student")
	public String students(Model model, HttpServletRequest request) {
		CommonsUtil.setUserRole(model);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = this.userService.findUserByDNI(auth.getName());
		List<Student> students = studentService.findAll();
		List<User> usersList = this.userService.findAll();

		Student studentForm = new Student();
		studentForm.setUser(user);
		model.addAttribute("studentForm", studentForm);
		model.addAttribute("students", students);
		model.addAttribute("usersList", usersList);

		return "student";
	}

	@PostMapping("/student")
	public String subjectsPost(Model model, @Valid @ModelAttribute Student form, BindingResult bindingResult) {
		CommonsUtil.setUserRole(model);
		User user = userService.findUserByDNI(form.getUser().getDni());
		if (bindingResult.hasErrors()) {
			return "student";
		} else {

			Student student = form;
			student.setUser(user);
			studentService.save(student);
			model.addAttribute("message", "Se registró la entidad con éxito");
		}
		return "confirmation";
	}

	@GetMapping("/student/delete")
	public String studentDelete(Model model, @RequestParam("id") long id) {
		CommonsUtil.setUserRole(model);
		studentService.deleteById(id);
		model.addAttribute("message", "La operación se realizo exitosamente");
		return "confirmation";
	}

}
