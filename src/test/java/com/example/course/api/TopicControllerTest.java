package com.example.course.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.course.api.domain.TopicEntity;

import static org.junit.Assert.*;
import static org.skyscreamer.jsonassert.JSONAssert.*;

import org.json.JSONException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CourseApiDataApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TopicControllerTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	@Test
	public void addTopic() {

		TopicEntity topic = new TopicEntity();
		topic.setDesc("MicroService");
		topic.setId("106");
		topic.setName("Veeru");
		HttpEntity<TopicEntity> entity = new HttpEntity<TopicEntity>(topic, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/topics"),
				HttpMethod.POST, entity, String.class);

		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

		assertTrue(actual.contains("/topics"));

	}

	@Test
	public void testRetrieveStudentCourse() throws JSONException {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/topics/106"),
				HttpMethod.GET, entity, String.class);

		String expected = "{id:\"106\",name:\"Veeru\",desc:\"MicroService\"}";

		assertEquals(expected, response.getBody(), false);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;

	}
	
	

	
}
