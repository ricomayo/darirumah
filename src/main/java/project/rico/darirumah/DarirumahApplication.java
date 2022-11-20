package project.rico.darirumah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@ComponentScan({"controller"})
@SpringBootApplication(scanBasePackages = { "project.rico.darirumah.controller"})
public class DarirumahApplication {

	public static void main(String[] args) {
		SpringApplication.run(DarirumahApplication.class, args);
	}

}
