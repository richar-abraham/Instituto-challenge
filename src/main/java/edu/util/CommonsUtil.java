package edu.util;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import edu.entity.Constants;

public class CommonsUtil {

	public static void setUserRole(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) auth
				.getPrincipal();
		model.addAttribute("admin", isAdmin(principal));

	}

	public static boolean isAdmin(org.springframework.security.core.userdetails.User principal) {
		Collection<GrantedAuthority> authorities = principal.getAuthorities();
		Optional<GrantedAuthority> isAdmin = authorities.stream()
				.filter(x -> x.getAuthority().equals(Constants.ADMIN_ROLE)).findAny();
		return isAdmin.isPresent();
	}

}
