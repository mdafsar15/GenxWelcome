package com.genx.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.genx.dto.LoginForm;
import com.genx.dto.SignUpForm;
import com.genx.model.User;
import com.genx.repository.RoleRepository;
import com.genx.repository.UserRepository;
import com.genx.response.Response;
import com.genx.response.SignupResponse;
import com.genx.service.UserDetailsServiceImpl;
import com.genx.util.JwtProvider;

import io.swagger.annotations.ApiOperation;

import static com.genx.util.SecurityConstants.EXPIRATION_TIME;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/api/auth")
public class UserController {

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

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	JwtProvider j = new JwtProvider();

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginForm loginRequest, HttpServletRequest request,
		HttpServletResponse resp) throws IOException {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
//		user.setStatus(true);
		//userRepository.save(user);
		//@SuppressWarnings("unchecked")
//		List<String> msgs = (List<String>) request.getAttribute("MY_SESSION_MESSAGES");
//		if (msgs == null) {
//			msgs = new ArrayList<>();
//			 request.getSession().setAttribute("MY_SESSION_MESSAGES", jwt);
			//resp.sendRedirect(request.getContextPath() + "/");
		//}
		
//		String attributeName = request.getParameter("MY_SESSION_MESSAGES");
//		String attributeValue = request.getParameter("attributeValue");
//		request.getSession().setAttribute(attributeName, attributeValue);
//		resp.sendRedirect(request.getContextPath() + "/signin");

		
		return ResponseEntity.ok(new Response(jwt, "Bearer", EXPIRATION_TIME));

	}

	@PostMapping("/signup")
	public ResponseEntity<SignupResponse> registerUser(@RequestBody SignUpForm signUpRequest) {
		SignupResponse response = userDetailsServiceImpl.registerUser(signUpRequest);
		return new ResponseEntity<SignupResponse>(response, HttpStatus.OK);

	}

	@ApiOperation(value = "To verify registration of user")

	@PutMapping("verification/{token}")
	public ResponseEntity<SignupResponse> verifyRegistration(@PathVariable("token") String token) {

		if (userRepository.isVerified(token)) {
			return new ResponseEntity<SignupResponse>(HttpStatus.OK);
		}
		return new ResponseEntity<SignupResponse>(HttpStatus.BAD_REQUEST);

	}

}