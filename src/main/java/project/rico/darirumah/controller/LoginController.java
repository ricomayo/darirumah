package project.rico.darirumah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.rico.darirumah.config.AppConstant;
import project.rico.darirumah.exception.definition.CommonException;
import project.rico.darirumah.model.ResponseInfo;
import project.rico.darirumah.model.request.LoginRq;
import project.rico.darirumah.usecase.LoginUsescase;
import io.swagger.v3.oas.annotations.Operation;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;


@RestController
@RequestMapping("/api/v1")
@Validated
public class LoginController {
    @Autowired
    private LoginUsescase loginUsescase;

    @Operation(summary = "Login Validation")
    @PostMapping(value = "/validateLogin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addDirectMember(@RequestHeader(value = "request-date", required = false) @DateTimeFormat(pattern = AppConstant.DATE_TIME_FORMAT) Date requestAt,
                                             @RequestBody @Valid LoginRq loginRq,
                                             HttpServletRequest servletRequest) throws CommonException {

        /* invoke use case */
        ResponseInfo responseInfo = loginUsescase.doLogin(loginRq);
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }

}