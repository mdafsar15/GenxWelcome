package com.genex.service;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.genex.dto.LoginDto;
import com.genex.exception.UserDoesNotExistException;
import com.genex.model.User;
import com.genex.repository.UserRepository;
import com.genex.response.Response;
import com.genex.util.JwtGenerator;

@PropertySource(name = "user", value = { "classpath:response.properties", "classpath:userupdate.properties" })

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Environment environment;

	@Autowired
	private PasswordEncoder passwordEncode;



	@Override
	public Response login(LoginDto loginDTO)  {
		Optional<User> userCheck = userRepository.findByEmail(loginDTO.getEmail());

		if (!userCheck.isPresent()) {
			throw new UserDoesNotExistException(environment.getProperty("status.update.usernotexit"));
		}
		if (passwordEncode.matches(loginDTO.getPassword(), userCheck.get().getPassword())) {

			String token = JwtGenerator.generateToken(userCheck.get().getId());
			System.out.println(token);
		//	RedisService.setToken(token, String.valueOf(userCheck.get().getId()));
			//userCheck.get().setStatus(true);
			userRepository.save(userCheck.get());
			return new Response(HttpStatus.OK.value(), environment.getProperty("status.login.success"), token);
		}

		throw new UserDoesNotExistException(environment.getProperty("status.login.incorrectpassword"));

	}


}
