package edu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.entity.Teacher;
import edu.service.TeacherService;
import edu.util.CommonsUtil;

@Controller
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@Autowired
	public TeacherController() {
	}

	@GetMapping("/teacher")
	public String teachers(Model model, HttpServletRequest request) {
		CommonsUtil.setUserRole(model);
		List<Teacher> teachers = teacherService.findAll();

		Teacher teacherForm = new Teacher();
		model.addAttribute("teacherForm", teacherForm);
		model.addAttribute("teachers", teachers);

		return "teacher";
	}

	@PostMapping("/teacher")
	public String subjectsPost(Model model, @Valid @ModelAttribute Teacher form, BindingResult bindingResult) {
		CommonsUtil.setUserRole(model);
		Teacher teacher = form;
		teacherService.save(teacher);
		model.addAttribute("message", "Se registró la entidad con éxito");
		return "confirmation";
	}

	@GetMapping("/teacher/delete")
	public String subjectDelete(Model model, @RequestParam("id") long id) {
		CommonsUtil.setUserRole(model);
		teacherService.delete(id);
		model.addAttribute("message", "La operación se realizo exitosamente");
		return "confirmation";
	}

}
