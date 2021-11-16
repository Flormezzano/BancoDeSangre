package com.BancoDeSangre1.BancoDeSangre1;

import com.BancoDeSangre1.BancoDeSangre1.Repositorios.ProvinciaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BancoDeSangre1Application {

    @Autowired
    ProvinciaRepo pr;
	public static void main(String[] args) {
		SpringApplication.run(BancoDeSangre1Application.class, args);
	}
	}
        

