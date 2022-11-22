package project.rico.darirumah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.rico.darirumah.exception.definition.CommonException;
import project.rico.darirumah.model.ResponseInfo;
import project.rico.darirumah.model.login.LoginRq;
import project.rico.darirumah.usecase.LoginUsecase;
import io.swagger.v3.oas.annotations.Operation;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1")
@Validated
public class LoginController {
    @Autowired
    private LoginUsecase loginUsecase;

    @Operation(summary = "Login Validation")
    @PostMapping(value = "/validateLogin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> validateLogin( @RequestBody @Valid LoginRq loginRq,
                                             HttpServletRequest servletRequest) throws CommonException {

        /* invoke use case */
        ResponseInfo responseInfo = loginUsecase.doLogin(loginRq);
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }

}
