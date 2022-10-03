package com.angular.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.angular.app.model.Tarea;

public interface TareaRepository extends MongoRepository<Tarea, String> {

}
