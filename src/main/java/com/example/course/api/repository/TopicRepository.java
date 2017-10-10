package com.example.course.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.course.api.domain.TopicEntity;

public interface TopicRepository extends CrudRepository<TopicEntity, String> {
	TopicEntity findById(String id);

}
