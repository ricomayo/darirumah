package project.rico.darirumah.exception.definition;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import project.rico.darirumah.config.CompletionStatus;
import project.rico.darirumah.exception.model.ApiFault;
import project.rico.darirumah.exception.model.ExceptionEnum;
import project.rico.darirumah.util.StringTools;


@Slf4j
@Getter
@Setter
@Accessors(chain = true)
public class CommonException extends Exception {
    private CompletionStatus status;
    private String code;
    private String name;
    private String description;
    private String type;
    private String displayMessage;
    private Exception originException;
    private HttpStatus httpStatus;
    private ExceptionEnum exceptionEnum;

    public CommonException(ExceptionEnum exceptionEnum, String type, String displayMessage, String message) {
        super(message);
        this.name = exceptionEnum.name();
        this.code = exceptionEnum.code;
        this.type =type;
        this.displayMessage = displayMessage;
    }



    public CommonException(Exception e) {
        super(String.format("%s : %s | %s", ExceptionEnum.SER_UNEXPECTED_ERROR.code, ExceptionEnum.SER_UNEXPECTED_ERROR.name(), ExceptionUtils.getRootCauseMessage(e)), e.getCause());
        log.error(this.getMessage(), e);
        this.exceptionEnum = ExceptionEnum.SER_UNEXPECTED_ERROR;
        this.name = exceptionEnum.name();
        this.code = exceptionEnum.code;
        this.description = ExceptionUtils.getRootCauseMessage(e);
        this.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

    }
    public CommonException(String exceptionEnum) {
//        super(String.format("ErrorConfig -> %s", exceptionEnum));

            this.code = "01";
            this.status = CompletionStatus.BUSINESS_ERROR;
            this.type = "BadRequest";
            this.httpStatus = HttpStatus.BAD_REQUEST;
            this.displayMessage = exceptionEnum;

    }


    public CommonException(HttpStatus status, String code, String type, String message) {
        super(message);
        this.httpStatus = status;
        this.status = status.is5xxServerError() ? CompletionStatus.SYSTEM_ERROR : CompletionStatus.BUSINESS_ERROR;
        this.code = code;
        this.type = type;
        this.displayMessage = message;
    }

    public ApiFault getApiFault() {
        return new ApiFault()
                .setStatus(status)
                .setCode(code)
                .setType(type)
                .setMessage(displayMessage)
                .setDetail(type + ":" + super.getMessage());
    }
}
