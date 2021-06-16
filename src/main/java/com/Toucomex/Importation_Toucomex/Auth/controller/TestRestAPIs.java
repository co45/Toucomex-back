package com.Toucomex.Importation_Toucomex.Auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/lock")
public class TestRestAPIs {

	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}

	@GetMapping("/achat")
	@PreAuthorize("hasRole('ACHAT') or hasRole('ADMIN')")
	public String achatAccess() {
		return "User Content.";
	}

	@GetMapping("/commercial")
	@PreAuthorize("hasRole('COMMERCIAL') or hasRole('ADMIN')")
	public String commAccess() {
		return "User Content.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}}