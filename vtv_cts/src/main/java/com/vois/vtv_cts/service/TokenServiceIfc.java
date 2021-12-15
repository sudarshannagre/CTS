package com.vois.vtv_cts.service;

import com.nimbusds.jose.JOSEException;
import com.vois.vtv_cts.model.JwtRequest;
import com.vois.vtv_cts.model.JwtResponse;

public interface TokenServiceIfc {

	public JwtResponse generateJWTToken(JwtRequest request) throws JOSEException;
}
