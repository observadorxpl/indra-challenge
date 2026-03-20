package com.indra.indrachallenge;

import com.indra.indrachallenge.infraestructure.output.config.security.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class IndraChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(IndraChallengeApplication.class, args);
	}

}
