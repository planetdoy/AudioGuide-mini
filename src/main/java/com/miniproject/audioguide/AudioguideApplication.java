package com.miniproject.audioguide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AudioguideApplication {
	public static void main(String[] args){
		String getVersion = org.springframework.core.SpringVersion.getVersion();
		System.out.println("spring framework version : "+getVersion);
		SpringApplication.run(AudioguideApplication.class, args);
	}
}
