package com.polylink.usermicroservice.controllers;


import com.polylink.usermicroservice.dto.ResponseUser;
import com.polylink.usermicroservice.models.ERole;
import com.polylink.usermicroservice.models.Role;
import com.polylink.usermicroservice.models.User;
import com.polylink.usermicroservice.payload.request.LoginRequest;
import com.polylink.usermicroservice.payload.request.SignupRequest;
import com.polylink.usermicroservice.payload.response.JwtRegisterResponse;
import com.polylink.usermicroservice.payload.response.JwtResponse;
import com.polylink.usermicroservice.payload.response.MessageResponse;
import com.polylink.usermicroservice.repository.RoleRepository;
import com.polylink.usermicroservice.repository.UserRepository;
import com.polylink.usermicroservice.security.jwt.JwtUtils;
import com.polylink.usermicroservice.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;


	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		User user = userRepository.findByEmail(loginRequest.getEmail()).get();


		return ResponseEntity.ok(new JwtResponse(jwt,new ResponseUser(user),
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {


		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),signUpRequest.getFirstname(),signUpRequest.getLastname());

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "recruiter":
					Role adminRole = roleRepository.findByName(ERole.ROLE_RECRUITER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;

				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		User newuser = userRepository.save(user);
		String jwt = jwtUtils.generateJwtTokenRegister(newuser.getEmail());
		return ResponseEntity.ok(new JwtRegisterResponse(jwt,new ResponseUser(newuser)));
	}


}
