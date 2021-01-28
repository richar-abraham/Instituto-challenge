package edu.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.entity.Constants;
import edu.entity.Role;
import edu.entity.User;
import edu.form.UserForm;
import edu.service.RoleService;
import edu.service.UserService;
import edu.util.CommonsUtil;

@Controller
public class LoginController {

	private static final String ADMIN_ROLE = "ADMIN";
	private static final String USER_ROLE = "USER";
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");

		return modelAndView;
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String loginError(HttpServletRequest request) {
		return "login";
	}

	@RequestMapping(value = { "/error" }, method = RequestMethod.GET)
	public String error(HttpServletRequest request) {
		return "access-denied";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		CommonsUtil.setUserRole(model);
		UserForm user = new UserForm();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(Model model, @Valid UserForm userForm, BindingResult bindingResult) {
		CommonsUtil.setUserRole(model);
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByDNI(userForm.getDni());
		if (userExists != null) {
			bindingResult.rejectValue("dni", "error.user", "Ya existe ese DNI");
		}
		if (bindingResult.hasErrors()) {
//			modelAndView.setViewName("registration");
			modelAndView.addObject("message", "Usuario no se pudo grabar");
			modelAndView.setViewName("confirmation");
		} else {
			GrantedAuthority roleSelected = new SimpleGrantedAuthority(USER_ROLE);
			if (userForm.getAdmin()) {
				roleSelected = new SimpleGrantedAuthority(ADMIN_ROLE);
			}
			List<GrantedAuthority> authorities = Arrays.asList(roleSelected);
			Set<Role> roles = new HashSet<Role>();
			Optional<Role> rol = roleService.findByName(authorities.get(0).getAuthority());
			User user = new User();
			user.setActive(Constants.ACTIVE);
			user.setDni(userForm.getDni());
			user.setNumber(userForm.getNumber());
			if (rol.isPresent()) {
				roles.add(rol.get());
				user.setRoles(roles);
				userService.saveUser(user);
				modelAndView.addObject("message", "Usuario dado de alta correctamente");
				modelAndView.addObject("user", new UserForm());
			} else {
				modelAndView.addObject("message", "Usuario no se pudo grabar");
			}
			modelAndView.setViewName("confirmation");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(HttpServletRequest request) {
		return "about";
	}

}
