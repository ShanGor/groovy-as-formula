package tech.comfortheart.demo

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.io.Resource

@SpringBootApplication
class Application {
	@Value("classpath:formula/Decimal.groovy")
	lateinit var resource: Resource

	@Bean
	fun commandLineRunner(args: Array<String>): CommandLineRunner {
		return CommandLineRunner {
			println(String(resource.inputStream.readBytes()))
		}
	}
}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
