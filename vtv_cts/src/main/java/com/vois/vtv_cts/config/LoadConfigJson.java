package com.vois.vtv_cts.config;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.vois.vtv_cts.model.Token;

@Component
public class LoadConfigJson implements InitializingBean {
	
	public static List<Token> configTokenList = new LinkedList<Token>();

	@Override
	public void afterPropertiesSet() throws Exception {
		JsonNode node = new ObjectMapper().readTree(new ClassPathResource("config.json").getInputStream())
				.get("tokens");
		Iterator<JsonNode> it = node.iterator();

		while (it.hasNext()) {
			Gson gson = new Gson();
			Token token = gson.fromJson(it.next().toString(), Token.class);
			configTokenList.add(token);
			
			  if (Arrays.stream(token.getBclaims().getAud()).anyMatch("vCDN"::equals)) {
			  System.out.println(token.getBclaims()); }
			 
		}
	}

}
