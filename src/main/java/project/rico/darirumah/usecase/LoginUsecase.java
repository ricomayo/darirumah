package project.rico.darirumah.usecase;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import project.rico.darirumah.config.AppConstant;
import project.rico.darirumah.model.ResponseInfo;
import project.rico.darirumah.model.request.LoginRq;
import project.rico.darirumah.model.response.GenericRs;

@Slf4j
@Component

public class LoginUsecase extends BaseUsecase {

    public ResponseInfo doLogin(LoginRq loginRq){
        GenericRs body = new GenericRs();
        ResponseInfo responseInfo = new ResponseInfo().setBody(body);

        responseInfo.setSuccess();
        return responseInfo;
    }
}
