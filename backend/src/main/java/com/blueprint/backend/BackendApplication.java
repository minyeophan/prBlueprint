package com.blueprint.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.blueprint.backend.repository.UserRepository;
import com.blueprint.backend.entity.User;
@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	@Bean
	CommandLineRunner testRun(UserRepository userRepository) {
	return args -> {
		System.out.println("===Repository 테스트 시작===");
		User user = new User();
		userRepository.save(user);
		System.out.println("저장 완료");

		System.out.println("전체 유저수: " +userRepository.count());
		System.out.println("===Repository 테스트 종료===");
	};
}
}

