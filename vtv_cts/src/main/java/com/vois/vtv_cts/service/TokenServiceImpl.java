package com.vois.vtv_cts.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JOSEException;
import com.vois.vtv_cts.config.LoadConfigJson;
import com.vois.vtv_cts.model.JwtRequest;
import com.vois.vtv_cts.model.JwtResponse;
import com.vois.vtv_cts.model.Token;
import com.vois.vtv_cts.util.JWTKeyGenerator;

@Service
public class TokenServiceImpl implements TokenServiceIfc{
	
	@Autowired
	JWTKeyGenerator jwtKeyGenerator;
	
	@Override
	public JwtResponse generateJWTToken(JwtRequest request) throws JOSEException {
		
		return jwtKeyGenerator.getJWTToken(jwtKeyGenerator.generateKeys(), validateAudience(request).getBclaims());
		
	}   
	

	private Token validateAudience(JwtRequest request) {
		
		for(Token token : LoadConfigJson.configTokenList) {
			 
			if(Arrays.stream(token.getBclaims().getAud()).anyMatch(request.getAudience():: equalsIgnoreCase) || 
					Arrays.stream(token.getBclaims().getAud()).anyMatch(request.getAudience()::equalsIgnoreCase)) {
				return token;
			}else if(Arrays.stream(token.getBclaims().getAud()).anyMatch(request.getAudience()::equalsIgnoreCase)) {
				return token;
			}
		}
		return null;
	}

}
