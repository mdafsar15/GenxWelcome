package com.genex.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Response {
	private String message;
	private int statusCode;
	private String token;
	private List<Object> details;

	public Response(String message, int statusCode) {

		this.message = message;
		this.statusCode = statusCode;
	}

	public Response(int statusCode, String message, String token) {

		this.message = message;
		this.statusCode = statusCode;
		this.token = token;
	}

	public Response(String message, int statusCode, List<Object> details) {

		this.message = message;
		this.statusCode = statusCode;
		this.details = details;
	}

}
