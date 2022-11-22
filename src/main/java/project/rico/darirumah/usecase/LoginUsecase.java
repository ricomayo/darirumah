package project.rico.darirumah.usecase;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.rico.darirumah.config.AppConstant;
import project.rico.darirumah.exception.definition.CommonException;
import project.rico.darirumah.model.ResponseInfo;
import project.rico.darirumah.model.db.UserRef;
import project.rico.darirumah.model.login.LoginRq;
import project.rico.darirumah.model.response.GenericRs;
import project.rico.darirumah.service.LoginService;
import project.rico.darirumah.util.StringTools;

import java.util.List;

@Slf4j
@Component

public class LoginUsecase extends BaseUsecase {

    @Autowired
    LoginService loginService;

    public ResponseInfo doLogin(LoginRq loginRq) {
        GenericRs body = new GenericRs();
        ResponseInfo responseInfo = new ResponseInfo().setBody(body);
        try {
            List<UserRef> listUserRef = loginService.getLoginInfo(loginRq.getUserName());

            if (listUserRef != null && listUserRef.size()>0){
                UserRef userRef = listUserRef.stream()
                        .filter(d -> StringTools.equals(d.getUsername(), loginRq.getUserName()) &&
                                StringTools.equals(d.getPassword(), loginRq.getPassword())
                        ).findAny().orElse(null);
                if(userRef != null){
                    userRef.setPassword(null);
                    responseInfo.setSuccess(userRef);
                }else{
                    throw new CommonException("Wrong Password !");
                }
            }else{
                throw new CommonException("Username not found.");
            }

        } catch (CommonException e) {
            responseInfo.setCommonException(e);
        } catch (Exception e){
            responseInfo.setException(e);
        }
        return responseInfo;
    }
}
