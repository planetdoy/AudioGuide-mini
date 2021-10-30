package com.miniproject.audioguide;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties={"spring.config.location=/home/ec2-user/app/application-real-db.yml"})
class AudioguideApplicationTests {

	@Test
	void contextLoads() {
	}

}
