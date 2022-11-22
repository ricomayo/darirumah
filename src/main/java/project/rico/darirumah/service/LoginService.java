package project.rico.darirumah.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import project.rico.darirumah.config.AppConstant;
import project.rico.darirumah.config.AppProperties;
import project.rico.darirumah.exception.definition.CommonException;
import project.rico.darirumah.model.db.UserRef;
import project.rico.darirumah.repository.UserRepository;
import project.rico.darirumah.util.StringTools;

import java.util.List;

@Slf4j
@Service
public class LoginService {
    @Autowired
    @Qualifier(AppConstant.BEAN_APP_CONFIG)
    private AppProperties appProperties;

    @Autowired
    private UserRepository userRepository;

    public List<UserRef> getLoginInfo(String userName) {
         return userRepository.getLogin(userName);
    }


}
