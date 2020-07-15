package com.genx.response;

import org.springframework.http.HttpStatus;
import lombok.Data;

@Data
public class SignupResponse {

    private String msg ;
    private HttpStatus status;
	public SignupResponse(String msg, HttpStatus badRequest) {
		super();
		this.msg = msg;
		this.status = badRequest;
	}
	public SignupResponse() {
		super();
	}
    
}
