package project.rico.darirumah.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import project.rico.darirumah.config.AppConstant;
import project.rico.darirumah.config.AppProperties;



public class DefaultUsecase {
    @Autowired
    @Qualifier(AppConstant.BEAN_APP_CONFIG)
    protected AppProperties appProperties;


}
