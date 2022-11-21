package project.rico.darirumah;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import project.rico.darirumah.config.AppConstant;
import project.rico.darirumah.config.AppProperties;

import javax.sql.DataSource;

@EnableAsync
@SpringBootApplication
public class DarirumahApplication {


//	@Autowired
//	private AppProperties appProperties;
//
//	@Bean(name = AppConstant.BEAN_APP_CONFIG)
//	public AppProperties loadAppConfig(){
//		return appProperties;
//	}
//
//	@Bean(AppConstant.BEAN_JDBC_MASTERDATA_POSTGRES)
//	public JdbcTemplate jdbcTemplateMst(@Qualifier(AppConstant.BEAN_DS_MASTERDATA_POSTGRES) DataSource dataSource) {
//		return new JdbcTemplate(dataSource);
//	}

	public static void main(String[] args) {

		SpringApplication.run(DarirumahApplication.class, args);
	}

}
