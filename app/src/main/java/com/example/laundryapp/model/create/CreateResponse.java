package com.example.laundryapp.model.create;

public class CreateResponse{
	private String message;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"CreateResponse{" + 
			"message = '" + message + '\'' + 
			"}";
		}
}
