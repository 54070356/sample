package com.example.springredis.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.springredis.domain.SimpleEntity;

public interface SimpleEntityRepository extends CrudRepository<SimpleEntity, String> {

}
