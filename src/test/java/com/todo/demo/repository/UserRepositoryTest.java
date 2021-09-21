package com.todo.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.todo.demo.entity.User;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private UserRepository userRepository;
	
	

	@Test
	void save_user_record_test() {

		User expected_result = new User();
		expected_result.setName(UUID.randomUUID().toString());
		expected_result.setPassword(UUID.randomUUID().toString());
		
		User saved_result = userRepository.save(expected_result);
		
		User actual_result = testEntityManager.find(User.class, saved_result.getName());
		
		assertThat(actual_result).isEqualTo(expected_result);

	
	}
}
