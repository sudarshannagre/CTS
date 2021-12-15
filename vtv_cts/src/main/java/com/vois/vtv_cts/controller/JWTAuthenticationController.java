package com.vois.vtv_cts.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vois.vtv_cts.model.JwtRequest;
import com.vois.vtv_cts.service.TokenServiceIfc;

@RestController
@CrossOrigin
public class JWTAuthenticationController {
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	TokenServiceIfc tokenServiceIfc;

	@RequestMapping({ "/" })
	public String hello() {
		return "Hello World";
	}

	@RequestMapping(value = "/v1/token", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {
		
		if(!request.getHeader("typ").equalsIgnoreCase("JWT")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Type not Mentioned in Header");
		}else {
			return ResponseEntity.ok(tokenServiceIfc.generateJWTToken(authenticationRequest));
					
		}
	}
}
