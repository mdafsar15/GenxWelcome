package com.genex.service;

import com.genex.dto.LoginDto;
import com.genex.response.Response;

public interface UserService {

	public Response login(LoginDto loginDTO);

}
