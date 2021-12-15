package com.vois.vtv_cts;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.vois.vtv_cts.model.Token;

public class Demo {

	public static void main(String[] args) throws JsonProcessingException, IOException {
		
		JsonNode  node = new ObjectMapper().readTree(new ClassPathResource("config.json").getInputStream()).get("tokens");
		Iterator<JsonNode> it = node.iterator();
		
		while(it.hasNext()) {
			 Gson gson = new Gson();  
			 Token t= gson.fromJson(it.next().toString(),Token.class);
			 
			if(Arrays.stream(t.getBclaims().getAud()).anyMatch("vCDN"::equals)) {
				System.out.println(t.getBclaims());
			}
		}
	}
}
