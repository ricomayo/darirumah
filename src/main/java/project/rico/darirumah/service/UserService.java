package project.rico.darirumah.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import project.rico.darirumah.config.AppConstant;
import project.rico.darirumah.config.AppProperties;
import project.rico.darirumah.model.request.CreateUserRq;
import project.rico.darirumah.model.request.UpdateUserRq;
import project.rico.darirumah.repository.UserRepository;

@Slf4j
@Service
public class UserService {
    @Autowired
    @Qualifier(AppConstant.BEAN_APP_CONFIG)
    private AppProperties appProperties;

    @Autowired
    private UserRepository userRepository;

    public String createUser(CreateUserRq createUserRq) {

        return userRepository.insertUser(createUserRq.getUserName(), createUserRq.getName(), createUserRq.getPassword(), createUserRq.getAddress(), createUserRq.getHandphone());
    }

    public int updateData(int iduser, UpdateUserRq updateUserRq) {

        return userRepository.updateData(iduser, updateUserRq.getOldPassword(), updateUserRq.getName(), updateUserRq.getAddress(), updateUserRq.getHandphone());
    }

    public String updatePassword(String idUser, UpdateUserRq updateUserRq) {

        return userRepository.updatePassword(idUser, updateUserRq.getOldPassword(), updateUserRq.getNewPassword());
    }
}
