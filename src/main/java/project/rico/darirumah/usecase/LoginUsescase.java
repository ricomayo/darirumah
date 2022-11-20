package project.rico.darirumah.usecase;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import project.rico.darirumah.model.ResponseInfo;
import project.rico.darirumah.model.request.LoginRq;
import project.rico.darirumah.model.response.GenericRs;

@Slf4j
@Component
public class LoginUsescase extends DefaultUsecase{

    public ResponseInfo doLogin(LoginRq loginRq){
        GenericRs body = new GenericRs();
        ResponseInfo responseInfo = new ResponseInfo().setBody(body);

        responseInfo.setSuccess();
        return responseInfo;
    }
}
