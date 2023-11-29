package com.joaoflaviofreitas.openlabsdemo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Swagger", version = "1", description = "Api de notas de filme"))
public class OpenLabsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenLabsDemoApplication.class, args);
	}

}
