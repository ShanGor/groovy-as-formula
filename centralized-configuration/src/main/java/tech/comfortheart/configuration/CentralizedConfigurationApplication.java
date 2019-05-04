package tech.comfortheart.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class CentralizedConfigurationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralizedConfigurationApplication.class, args);
	}

}
