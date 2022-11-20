package project.rico.darirumah.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import project.rico.darirumah.config.CompletionStatus;
import project.rico.darirumah.exception.definition.CommonException;
import project.rico.darirumah.exception.model.ExceptionInfo;


@Getter
@Setter
@ToString
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericRs implements DefaultResponse {
    private String status;
    private String code;
    private String message;
    private Object data;

    @Override
    public void setSuccess() {
        this.status = "ok";
        this.code = "00";
        this.message = "success";
    }

    @Override
    public void setSuccess(Object data) {
        setSuccess();
        this.data = data;
    }

    @Override
    public void overrideException(ExceptionInfo exception) {
        if(exception.getStatus() != null) {
            this.status = exception.getStatus();
        }
        if(exception.getCode() != null) {
            this.code = exception.getCode();
        }
        if(exception.getMessage() != null) {
            this.message = exception.getMessage();
        }
    }

    public void setCommonException(CommonException commonException) {
        this.code = commonException.getCode();
        this.status = commonException.getStatus().equals(CompletionStatus.BUSINESS_ERROR)?"failed":"error";
        this.message = commonException.getDisplayMessage();
    }

}
