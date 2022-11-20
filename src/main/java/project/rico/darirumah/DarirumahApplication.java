package project.rico.darirumah;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import project.rico.darirumah.config.AppConstant;
import project.rico.darirumah.config.AppProperties;

@EnableAsync
@SpringBootApplication
public class DarirumahApplication {
	@Autowired
	private AppProperties appProperties;
	@Bean(name = AppConstant.BEAN_APP_CONFIG)
	public AppProperties loadAppConfig(){
		return appProperties;
	}

	public static void main(String[] args) {
		SpringApplication.run(DarirumahApplication.class, args);
	}

}
