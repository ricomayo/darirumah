package project.rico.darirumah.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import project.rico.darirumah.config.AppConstant;
import project.rico.darirumah.config.AppProperties;


@Slf4j
@Component
@Qualifier(AppConstant.BEAN_APP_CONFIG)
public class BaseUsecase {
    @Autowired
    @Qualifier(AppConstant.BEAN_APP_CONFIG)
    protected AppProperties appProperties;


}
