package com.example.course.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course.api.domain.TopicEntity;
import com.example.course.api.repository.TopicRepository;

@Service
public class TopicService {
	@Autowired
	private TopicRepository topicRepo;

	public List<TopicEntity> getAllTopics() {
		List<TopicEntity> topicList = new ArrayList<>();
		topicRepo.findAll().forEach(topicList::add);
		return topicList;

	}

	public TopicEntity getTopic(String id) {

		return topicRepo.findOne(id);
	}

	public TopicEntity add(TopicEntity topic) {
		 return topicRepo.save(topic);

	}

	public void updateTopic(TopicEntity topic, String id) {

		topicRepo.save(topic);
	}

	public void deleteTopic(String id) {

		topicRepo.delete(id);
	}
}
