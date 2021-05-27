package com.margotekstil;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/*
@EnableJpaRepositories("com.margotekstil.*") 
@EntityScan("com.margotekstil.*")
@ComponentScan("com.margotekstil.*")*/
@SpringBootApplication(/*exclude= {SecurityAutoConfiguration.class}*/)
public class MargotekstilApplication {

	public static void main(String[] args) {
		SpringApplication.run(MargotekstilApplication.class, args);
	}

}
