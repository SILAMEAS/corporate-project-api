package com.corporate.project.laza_api;

import com.corporate.project.laza_api.model.entity.React;
import com.corporate.project.laza_api.repository.ReactRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest(classes = LazaApiApplication.class)
class LazaApiApplicationTests {

	@Autowired
	private ReactRepository repository;

	@Test
	void testFindAllReact(){
		List<React> reacts = repository.findAll();
		reacts.forEach(react -> System.out.println(react.getName() + react.getId()));
	}

	@Test
	void testDeleteReactById(){
		repository.deleteById(1L);
	}

}
