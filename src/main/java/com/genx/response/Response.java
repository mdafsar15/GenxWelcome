package com.genx.response;

import lombok.Data;

@Data
public class Response {
	private String token;
	private String type;
	private long tokenExpiration;

	public Response() {
		super();
	}

	public Response(String token, String type, long tokenExpiration) {
		super();
		this.token = token;
		this.type = type;
		this.tokenExpiration = tokenExpiration;
	}

}