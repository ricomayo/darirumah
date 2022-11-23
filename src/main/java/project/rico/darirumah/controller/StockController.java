package project.rico.darirumah.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.rico.darirumah.exception.definition.CommonException;
import project.rico.darirumah.model.ResponseInfo;
import project.rico.darirumah.model.request.ModifyProductRq;
import project.rico.darirumah.usecase.StockUsecase;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Validated
public class StockController {
    @Autowired
    StockUsecase stockUsecase;

    @Operation(summary = "Get List Stock")
    @GetMapping(value = "/getListStock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getListStock(@RequestParam(value = "productcode", required = false) String productCode,
                                          @RequestParam(value = "productname", required = false) String productName,
                                          @RequestParam(value = "type", required = false) String type) throws CommonException {
        /* invoke use case */
        ResponseInfo responseInfo = stockUsecase.getStock(productCode, productName, type);
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }

    @Operation(summary = "Add New Stock")
    @PostMapping(value = "/addStock/{iduser}/{productcode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addStock(@PathVariable(value = "iduser") int idUser,
                                      @PathVariable(value = "productcode") String productCode,
                                      @RequestParam(value = "qty") int qty) throws CommonException {
        /* invoke use case */
        ResponseInfo responseInfo = stockUsecase.addNewStock(idUser, productCode,qty);
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }

    @Operation(summary = "Update Stock")
    @PutMapping(value = "/updateStock/{iduser}/{idstock}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStock(@PathVariable(value = "iduser") int idUser,
                                         @PathVariable(value = "idstock") int idStock,
                                         @RequestParam(value = "idproduct") int idProduct,
                                         @RequestParam(value = "qty") int qty) throws CommonException {
        /* invoke use case */
        ResponseInfo responseInfo = stockUsecase.updateStock(idUser, idStock,idProduct, qty);
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }

    /*@Operation(summary = "Delete Stock")
    @DeleteMapping(value = "/deleteStock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteStock() throws CommonException {
        *//* invoke use case *//*
        ResponseInfo responseInfo = stockUsecase.deleteStock();
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }*/

}
