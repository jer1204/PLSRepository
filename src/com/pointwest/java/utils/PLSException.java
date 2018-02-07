package com.pointwest.java.utils;

public class PLSException extends Exception {

	public String displayMessage;
	
	public PLSException(String msg) {
		this.displayMessage = msg;
	}
	
	public String getDisplayMessage() {
		return displayMessage;
	}
}
