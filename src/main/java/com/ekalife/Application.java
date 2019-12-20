package com.ekalife;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.ekalife.utils.PDF;

@SpringBootApplication
@Configuration
@ImportResource("classpath:boot.xml")
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		PDF pdf = new PDF();
		pdf.generatePDF();
	}

}
