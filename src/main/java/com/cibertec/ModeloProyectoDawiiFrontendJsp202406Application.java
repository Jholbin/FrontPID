package com.cibertec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ModeloProyectoDawiiFrontendJsp202406Application {
	

	public static void main(String[] args) {
		SpringApplication.run(ModeloProyectoDawiiFrontendJsp202406Application.class, args);
	}
	
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
