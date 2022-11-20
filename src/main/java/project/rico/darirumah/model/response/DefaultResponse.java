package project.rico.darirumah.model.response;

import project.rico.darirumah.exception.definition.CommonException;
import project.rico.darirumah.exception.model.ExceptionInfo;

public interface DefaultResponse {
    void setSuccess();
    void setSuccess(Object data);
    void overrideException(ExceptionInfo exception);
    void setCommonException(CommonException e);
}
