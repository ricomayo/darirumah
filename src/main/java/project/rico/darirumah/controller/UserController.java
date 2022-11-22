package project.rico.darirumah.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.rico.darirumah.exception.definition.CommonException;
import project.rico.darirumah.model.ResponseInfo;
import project.rico.darirumah.model.request.CreateUserRq;
import project.rico.darirumah.model.request.UpdateUserRq;
import project.rico.darirumah.usecase.UserUsecase;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Validated
public class UserController {
    @Autowired
    UserUsecase userUsecase;

    @Operation(summary = "Create User")
    @PostMapping(value = "/createUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserRq createUserRq) throws CommonException {
        /* invoke use case */
        ResponseInfo responseInfo = userUsecase.createUser(createUserRq);
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }

    @Operation(summary = "Update User")
    @PutMapping(value = "/updateUser/{iduser}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@PathVariable(value = "iduser") int idUser,
                                        @RequestBody UpdateUserRq updateUserRq) throws CommonException {
        /* invoke use case */
        ResponseInfo responseInfo = userUsecase.updateUser(idUser, updateUserRq);
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }

}
