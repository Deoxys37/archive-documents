package com.alagezia37.archivedocuments.model;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.stereotype.Component;

/* @FieldMatch(first="password", second="password_verify", message = "Паролі повинні співпадати.") */

@Component
public class User {
	@Pattern(regexp="[A-Za-zА-ЯЄІа-яєі0-9]*")
	@Size(min=5, max=20)
	private String passport;
	
	@Pattern(regexp="[^ ]*")
	@Size(min=8, max=30)
	private String password;
	
	@Pattern(regexp="[A-Za-zА-ЯЄІа-яєі]+[A-Za-zА-ЯЄІа-яєі']*[A-Za-zА-ЯЄІа-яєі]+")
	@Size(min=2, max=20)
	private String name;
	
	@Pattern(regexp="[A-Za-zА-ЯЄІа-яєі]+[A-Za-zА-ЯЄІа-яєі']*[A-Za-zА-ЯЄІа-яєі]+")
	@Size(min=2, max=20)
	private String surname;
	
	private boolean enabled;
	private String authority;
	private List<Document> documents;
	
	public User(String name, String surname, String password, String passport, boolean enabled, String authority) {
		super();
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.passport = passport;
		this.enabled = enabled;
		this.authority = authority;
	}
	
	public User(String name, String surname, String password, String passport) {
		super();
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.passport = passport;
	}

	public User() {	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public boolean validatePassword(String password) {
		boolean correct = false;
		try {
			correct = Password.validatePassword(password, this.password) ? true : false;
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return correct;
	}
	
	public boolean setRegistrationParameters() {
		name = WordUtils.capitalize(name);
		surname = WordUtils.capitalize(surname);
		passport = passport.toUpperCase();
		enabled = true;
		authority = "ROLE_USER";
		try {
			password = Password.getHash(password);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", surname=" + surname
				+ ", password=" + password + ", passport=" + passport + "]";
	}
}