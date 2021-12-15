package com.vois.vtv_cts.model;

import java.io.Serializable;

public class JwtResponse implements Serializable{
	
	private static final long serialVersionUID = -6245189744785030611L;
	
	private String aud[];
	private String exp;
	private String token;
	public String[] getAud() {
		return aud;
	}
	public void setAud(String[] aud) {
		this.aud = aud;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public JwtResponse() {
		super();
	}
}
