package br.com.compasso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories
public class CidadeApplication {

	public static void main(String[] args) {
			
		 SpringApplication app = new SpringApplication(CidadeApplication.class);	        
	     app.run(args);
	}
}
