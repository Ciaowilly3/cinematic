package com.cinematic.cinematic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableSwagger2
public class CinematicApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinematicApplication.class, args);
	}

}
//todo: sistemare i tag swagger FATTO
//todo: vedere userName null
//todo: chiamata rest a dummyJson
//todo: chiamare Soap
//todo: chiamare Grpc
//todo: redis

