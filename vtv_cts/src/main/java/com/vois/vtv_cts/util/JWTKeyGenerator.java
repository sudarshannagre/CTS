package com.vois.vtv_cts.util;

import java.util.Arrays;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.vois.vtv_cts.model.Bclaims;
import com.vois.vtv_cts.model.JwtResponse;

import static com.vois.vtv_cts.constant.CTSConstant.*;

@Component
public class JWTKeyGenerator {
	
	RSAKey rsaJWK = null;
	
	public RSAKey generateKeys() throws JOSEException {
		rsaJWK = new RSAKeyGenerator(2048)
			    .keyID("VOIS")
			    .generate();
		System.out.println(rsaJWK);
		return rsaJWK;
	}
	
	public JwtResponse getJWTToken(RSAKey key,Bclaims bClaims) throws JOSEException {
		// Create RSA-signer with the private key
		JWSSigner signer = new RSASSASigner(key);
		JWTClaimsSet claimsSet = null;
		Date expiry = new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * bClaims.getExp());
		
		if(Arrays.stream(bClaims.getAud()).anyMatch(NPAW::equalsIgnoreCase) || 
				Arrays.stream(bClaims.getAud()).anyMatch(EPGOCD::equalsIgnoreCase)){
			
			// Prepare JWT with claims set
			claimsSet = new JWTClaimsSet.Builder()
				.subject("JWT Token")
				.audience(Arrays.asList(bClaims.getAud()))
			    .issuer(bClaims.getIss())
			    .expirationTime(expiry)
			    .issueTime(new Date())
			    .jwtID("")
			    .build();
			
		}else if(Arrays.stream(bClaims.getAud()).anyMatch(VCDN::equalsIgnoreCase)) {
			// Prepare JWT with claims set
			claimsSet = new JWTClaimsSet.Builder()
			    .subject("JWT Token")
			    .audience(Arrays.asList(bClaims.getAud()))
			    .expirationTime(expiry)
			    .build();
			
		}

		SignedJWT signedJWT = new SignedJWT(
			    new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(key.toRSAPublicKey().toString()).build(),
			    claimsSet);
		// Compute the RSA signature
		signedJWT.sign(signer);
		
		JwtResponse response = new JwtResponse();
		response.setAud(bClaims.getAud());
		System.out.println(key);
		System.out.println(expiry);
		response.setExp(""+expiry);
		response.setToken(signedJWT.serialize());
		return response;
	}
	
}
