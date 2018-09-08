package com.example.springredis.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springredis.domain.AId;
import com.example.springredis.domain.ComplexIdEntity;
import com.example.springredis.domain.SimpleEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepApp {
	@Autowired
	SimpleEntityRepository simpleRepo;
	
	@Autowired
	TestEntityRepository complexRepo;

	@Test
	public void testComplex() {
		AId id = new AId();
		id.setSubId1(1);
		id.setSubId2("2");
		
		ComplexIdEntity entity = new ComplexIdEntity();
		entity.setId(id);
		complexRepo.save(entity);

		complexRepo.findById(entity.getId());

		complexRepo.count();

		complexRepo.delete(entity);

	}
	
	
	@Test
	public void testSimple() {
		 
		
		SimpleEntity entity1 = new SimpleEntity();
		entity1.setId("simpleEntity1");
		
		SimpleEntity entity2 = new SimpleEntity();
		entity2.setId("simpleEntity2");
		
		simpleRepo.save(entity1);
		simpleRepo.save(entity2);
		

		simpleRepo.findById(entity1.getId());
		simpleRepo.findById(entity2.getId());

		simpleRepo.count();

		//simpleRepo.delete(entity);

	}

	private void print(List<byte[]> list) {
		list.forEach(item -> {
			System.out.println(new String(item));
		});
	}

}
