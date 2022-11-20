package project.rico.darirumah.model.response;

import project.rico.darirumah.exception.model.ExceptionInfo;

public interface BaseResponse {
    void setSuccess();
    void setSuccess(Object data);
    void overrideException(ExceptionInfo exception);

}
