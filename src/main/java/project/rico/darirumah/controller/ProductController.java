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
import project.rico.darirumah.usecase.ProductUsecase;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Validated
public class ProductController {
    @Autowired
    ProductUsecase productUsecase;

    @Operation(summary = "Get List Product")
    @GetMapping(value = "/getListProduct/{productcode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getListProduct(@RequestHeader(value = "iduser", required = false) int idUser,
                                            @PathVariable(value = "productcode") String productCode,
                                            @RequestParam(value = "productname", required = false) String productName,
                                            @RequestParam(value = "type", required = false) String type,
                                            @RequestBody @Valid ModifyProductRq modifyProductRq) throws CommonException {
        /* invoke use case */
        ResponseInfo responseInfo = productUsecase.getProduct(productCode, productName, type, idUser);
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }

    @Operation(summary = "Add New Product")
    @PostMapping(value = "/addProduct/{iduser}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addProduct(@PathVariable(value = "iduser") int idUser,
                                        @RequestBody @Valid ModifyProductRq modifyProductRq) throws CommonException {
        /* invoke use case */
        ResponseInfo responseInfo = productUsecase.addNewProduct(idUser, modifyProductRq);
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }

    @Operation(summary = "Update Product")
    @PutMapping(value = "/updateProduct/{iduser}/{productcode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProduct(@PathVariable(value = "iduser") int idUser,
                                           @PathVariable(value = "productcode") String productCode,
                                           @RequestBody ModifyProductRq modifyProductRq) throws CommonException {
        /* invoke use case */
        ResponseInfo responseInfo = productUsecase.updateProduct(idUser, productCode, modifyProductRq);
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }

    @Operation(summary = "Delete Product")
    @DeleteMapping(value = "/deleteProduct/{iduser}/{productcode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "iduser") int idUser,
                                           @PathVariable(value = "productcode") String productCode,
                                           @RequestBody ModifyProductRq modifyProductRq) throws CommonException {
        /* invoke use case */
        ResponseInfo responseInfo = productUsecase.deleteProduct(idUser, productCode);
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }

}
