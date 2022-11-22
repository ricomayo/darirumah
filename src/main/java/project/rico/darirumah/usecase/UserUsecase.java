package project.rico.darirumah.usecase;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.rico.darirumah.config.AppConstant;
import project.rico.darirumah.exception.definition.CommonException;
import project.rico.darirumah.model.ResponseInfo;
import project.rico.darirumah.model.request.CreateUserRq;
import project.rico.darirumah.model.request.UpdateUserRq;
import project.rico.darirumah.model.response.GenericRs;
import project.rico.darirumah.service.UserService;
import project.rico.darirumah.util.StringTools;

@Slf4j
@Component
public class UserUsecase extends BaseUsecase {
    @Autowired
    UserService userService;

    public ResponseInfo createUser(CreateUserRq createUserRq) {
        GenericRs body = new GenericRs();
        ResponseInfo responseInfo = new ResponseInfo().setBody(body);

        try {
            String i = userService.createUser(createUserRq);

            responseInfo.setSuccess(i);
        } catch (Exception e) {
            responseInfo.setException(e);
        }
        return responseInfo;
    }

    public ResponseInfo updateUser(String idUser, UpdateUserRq updateUserRq) {
        GenericRs body = new GenericRs();
        ResponseInfo responseInfo = new ResponseInfo().setBody(body);
        StringBuilder respons = new StringBuilder("Update");

        try {
            if (!StringTools.isEmptyOrNull(updateUserRq.getName()) ||
                    !StringTools.isEmptyOrNull(updateUserRq.getAddress()) ||
                    !StringTools.isEmptyOrNull(updateUserRq.getHandphone())) {
                System.out.println("masuk data");
                int intIdUser = Integer.valueOf(idUser);
                int i = userService.updateData(intIdUser, updateUserRq);
                if (i > 0) {
                    respons.append(" Data ");
                } else {
                    throw new CommonException("User Not Found");
                }
            }

            if (updateUserRq.getOldPassword() != null && updateUserRq.getNewPassword() != null) {
                System.out.println("masuk pass");
                String result = userService.updatePassword(idUser, updateUserRq);
                if (result.equals(AppConstant.UPDATE_PASSWORD.NOT_FOUND.toString())) {
                    throw new CommonException("User Not Found");
                } else if (result.equals(AppConstant.UPDATE_PASSWORD.NOT_MATCH.toString())) {
                    throw new CommonException("Password Not Match");
                } else {
                    respons.append(" Password ");
                }
            }

            respons.append("Success.");
            responseInfo.setSuccess(respons.toString());
        } catch (Exception e) {
            responseInfo.setException(e);
        }
        return responseInfo;
    }
}
