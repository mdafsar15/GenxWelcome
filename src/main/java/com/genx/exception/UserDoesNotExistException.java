package com.genx.exception;

@SuppressWarnings("serial")
public class UserDoesNotExistException extends RuntimeException {
	
	String massage;
	public UserDoesNotExistException() {
		
	}
	
    public UserDoesNotExistException(String massage) {
             super(massage);		
	}
	
    public UserDoesNotExistException(String massage,String response) {
        super(massage);

}
}
