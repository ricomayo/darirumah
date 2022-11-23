package project.rico.darirumah.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.rico.darirumah.exception.definition.CommonException;
import project.rico.darirumah.model.ResponseInfo;
import project.rico.darirumah.model.request.EndOrderRq;
import project.rico.darirumah.model.request.ModifyProductRq;
import project.rico.darirumah.model.request.OrderRq;
import project.rico.darirumah.service.OrderService;
import project.rico.darirumah.usecase.OrderUsecase;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Validated
public class OrderController {
    @Autowired
    OrderUsecase orderUsecase;

    @Operation(summary = "Order Product")
    @PostMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addProduct(@RequestBody @Valid OrderRq orderRq) throws CommonException {
        /* invoke use case */
        ResponseInfo responseInfo = orderUsecase.placeOrder(orderRq);
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }

    @Operation(summary = "Cancel Product")
    @PostMapping(value = "/cancel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> cancelOrder(@RequestBody @Valid EndOrderRq orderRq) throws CommonException {
        /* invoke use case */
        ResponseInfo responseInfo = orderUsecase.cancelOrder(orderRq);
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }
}
