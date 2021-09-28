package com.cst438.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.cst438.domain.CourseDTOG;

public class RegistrationServiceREST extends RegistrationService {

	
	RestTemplate restTemplate = new RestTemplate();
	
	@Value("${registration.url}") 
	String registration_url;
	
	public RegistrationServiceREST() {
		System.out.println("REST registration service ");
	}
	
	@Override
	@PostMapping("/final-grades")
	public void sendFinalGrades(int course_id , CourseDTOG courseDTO) { 
		
		ResponseEntity<CourseDTOG> response = restTemplate.postForEntity(registration_url + "/course/" + course_id, courseDTO, CourseDTOG.class);
		HttpStatus rc = response.getStatusCode();
		System.out.println("HttpStatus: " + rc);
		CourseDTOG returnObject = response.getBody();
		System.out.println(returnObject);
		
	}
}