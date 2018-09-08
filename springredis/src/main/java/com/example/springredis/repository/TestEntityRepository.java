package com.example.springredis.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.springredis.domain.AId;
import com.example.springredis.domain.ComplexIdEntity;

public interface TestEntityRepository extends CrudRepository<ComplexIdEntity, AId> {

}
