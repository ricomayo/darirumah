package project.rico.darirumah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan({"controllers"})
@SpringBootApplication(scanBasePackages = { "project.rico.darirumah"})
public class DarirumahApplication {

	public static void main(String[] args) {
		SpringApplication.run(DarirumahApplication.class, args);
	}

}
