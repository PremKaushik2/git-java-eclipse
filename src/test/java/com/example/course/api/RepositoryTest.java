package com.example.course.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.example.course.api.domain.TopicEntity;
import com.example.course.api.repository.TopicRepository;

/**
 * Junit tests for {@link TopicRepository} to test the Repository.
 * 
 * @author PremSharma
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class RepositoryTest {
	@Autowired
	TopicRepository repository;

	/**
	 * Tests inserting a user and asserts it can be loaded again.
	 */
	@Test
	public void testInsert() {

		TopicEntity topic = new TopicEntity();
		topic.setDesc("MongoDb");
		topic.setId("109");
		topic.setName("NO-SQL");
		topic = repository.save(topic);
		assertThat(repository.findOne(topic.getId())).isNotNull();
		assertThat(repository.findOne(topic.getId())).isEqualTo(topic);
	}
	
	 /*@Test
	    public void testCreateWithId() {

	        Exception exception = null;

	        TopicEntity topic = new TopicEntity();
			topic.setDesc("Neo4j");
			topic.setId("120");
			topic.setName("Graph");

	        try {
	        	repository.save(topic);
	        } catch (EntityExistsException e) {
	            exception = e;
	        }
            assertThat(exception,notNullValue());
	        Assert.assertNotNull("failure - expected exception", exception);
	        Assert.assertTrue("failure - expected EntityExistsException",
	                exception instanceof EntityExistsException);
            assertThat("failure - expected EntityExistsException",is(exception instanceof EntityExistsException));

	    }*/


	@Test
	public void saveAndFindById() {

		TopicEntity topic = new TopicEntity();
		topic.setDesc("MongoDb");
		topic.setId("109");
		topic.setName("NO-SQL");
		topic = repository.save(topic);
		List<TopicEntity> topicList = new ArrayList<>();
		repository.findAll().forEach(topicList::add);
		assertThat(topicList).contains(topic);
		assertThat(topic).isEqualTo(repository.findOne("109"));
		assertThat(repository.findById(topic.getId())).isEqualTo(topic);

	}

}