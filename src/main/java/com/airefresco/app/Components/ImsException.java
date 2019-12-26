package com.airefresco.app.Components;

public class ImsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	/**
	 * CODES TO 200 FROM 210 -> WEB
	 * CODES TO 300 FROM 310 -> SQL
	 */
	private int code;
	private String type;
	
	public ImsException (String Message,int code,String type) {
		super(Message);
		this.message=Message;
		this.code=code;
		this.type=type;
	}
	
	public String getMesagge() {
		return this.message;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getType() {
		return this.type;
	}

}
