package br.uece.sigdp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API de Empréstimo", version = "1.0", description = "API para gerenciamento de empréstimos"))
public class EmprestimoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmprestimoApiApplication.class, args);
	}

}
