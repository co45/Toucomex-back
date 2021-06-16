package com.Toucomex.Importation_Toucomex.Auth.controller;

import com.Toucomex.Importation_Toucomex.Auth.message.request.LoginForm;
import com.Toucomex.Importation_Toucomex.Auth.message.request.SignUpForm;
import com.Toucomex.Importation_Toucomex.Auth.message.response.JwtResponse;
import com.Toucomex.Importation_Toucomex.Auth.message.response.ResponseMessage;
import com.Toucomex.Importation_Toucomex.Auth.model.RoleName;
import com.Toucomex.Importation_Toucomex.Auth.model.Role;
import com.Toucomex.Importation_Toucomex.Auth.model.User;
import com.Toucomex.Importation_Toucomex.Auth.repository.RoleRepository;
import com.Toucomex.Importation_Toucomex.Auth.repository.UserRepository;
import com.Toucomex.Importation_Toucomex.Auth.security.jwt.JwtProvider;
import com.Toucomex.Importation_Toucomex.Auth.security.services.UserDetailsServiceImpl;
import com.Toucomex.Importation_Toucomex.Auth.security.services.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins ="*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthRestAPIs  {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getPrenom(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getDateOfBirth(), signUpRequest.getPhone(), signUpRequest.getDepartement(),signUpRequest.getPhoto());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(RoleName.ROLE_COMMERCIAL)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
					case "stock":
						Role stockRole = roleRepository.findByName(RoleName.ROLE_STOCK)
								.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
						roles.add(stockRole);
						break;
					case "achat":
						Role achatRole = roleRepository.findByName(RoleName.ROLE_ACHAT)
								.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
						roles.add(achatRole);
						break;
					case "admin":
						Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
						roles.add(adminRole);
						break;
					default:
						Role userRole = roleRepository.findByName(RoleName.ROLE_COMMERCIAL)
								.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
						roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		userRepository.save(user);

		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}



}
