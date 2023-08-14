package com.kerem.userman.dto;

public class EmailPayload {
	private String toEmail;
	private String title;
	private String context;
	
	

	public EmailPayload() {}

	public EmailPayload(String toEmail, String title, String context) {
		super();
		this.toEmail = toEmail;
		this.title = title;
		this.context = context;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	
	

	
	
	
}
