package project.rico.darirumah.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author IDCB.RIEFKA RICO [idcb.riefkar@xl.co.id]
 * Created At 21/11/2022 17:34
 */
@Configuration
public class CloudConfig {
    @Autowired
	private AppProperties appProperties;

	@Bean(name = AppConstant.BEAN_APP_CONFIG)
	public AppProperties loadAppConfig(){
		return appProperties;
	}

	@Bean(AppConstant.BEAN_DS_MASTERDATA_POSTGRES)
	@ConfigurationProperties("jdbc-masterdata-postgres")
	public DataSource dataSourceMst() {
		return DataSourceBuilder.create().build();
	}

	@Bean(AppConstant.BEAN_JDBC_MASTERDATA_POSTGRES)
	public JdbcTemplate jdbcTemplateConf(@Qualifier(AppConstant.BEAN_DS_MASTERDATA_POSTGRES) DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}



}
