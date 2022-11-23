package project.rico.darirumah.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.rico.darirumah.exception.definition.CommonException;
import project.rico.darirumah.model.ResponseInfo;
import project.rico.darirumah.usecase.StockUsecase;

@RestController
@RequestMapping("/api/v1")
@Validated
public class SchedulerController {
    @Autowired
    StockUsecase stockUsecase;

    @Operation(summary = "Delete Stock")
    @DeleteMapping(value = "/deleteStock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteStock() throws CommonException {
        /* invoke use case */
        ResponseInfo responseInfo = stockUsecase.deleteStock();
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }
}
