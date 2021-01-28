package edu.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserForm {

	@NotNull
	@Size(min = 2, max = 10)
	private String dni;

	@NotNull
	@Size(min = 2, max = 10)
	private String number;
	private boolean admin;

	public boolean getAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
