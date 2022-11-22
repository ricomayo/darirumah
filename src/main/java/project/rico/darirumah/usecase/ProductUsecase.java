package project.rico.darirumah.usecase;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.rico.darirumah.config.AppConstant;
import project.rico.darirumah.exception.definition.CommonException;
import project.rico.darirumah.model.ResponseInfo;
import project.rico.darirumah.model.db.ProductRef;
import project.rico.darirumah.model.request.ModifyProductRq;
import project.rico.darirumah.model.response.GenericRs;
import project.rico.darirumah.service.ProductService;
import project.rico.darirumah.service.UserService;
import project.rico.darirumah.util.StringTools;

import java.util.List;

@Slf4j
@Component
public class ProductUsecase extends BaseUsecase {
    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    public ResponseInfo addNewProduct(int idUser, ModifyProductRq modifyProductRq) {
        GenericRs body = new GenericRs();
        ResponseInfo responseInfo = new ResponseInfo().setBody(body);
        String response = "";

        try {
            if (userService.getAccessLevel(idUser)) {
                response = productService.addProduct(idUser, modifyProductRq);
                if (StringTools.equals(response, AppConstant.MODIFY_PRODUCT.PRODUCT_EXIST.toString())) {
                    throw new CommonException("This Product Already Exist");
                }
            } else {
                throw new CommonException("You are not Authorized!!");
            }
            responseInfo.setSuccess(response);
        } catch (Exception e) {
            responseInfo.setException(e);
        }
        return responseInfo;
    }

    public ResponseInfo updateProduct(int idUser, String productCode, ModifyProductRq modifyProductRq) {
        GenericRs body = new GenericRs();
        ResponseInfo responseInfo = new ResponseInfo().setBody(body);
        StringBuilder response = new StringBuilder();
        try {
            if (userService.getAccessLevel(idUser)) {
                if (!StringTools.isEmptyOrNull(modifyProductRq.getProductName()) ||
                        !StringTools.isEmptyOrNull(modifyProductRq.getType()) ||
                        !StringTools.isEmptyOrNull(modifyProductRq.getUom())) {

                    int i = productService.updateProduct(idUser, productCode, modifyProductRq);
                    if (i > 0) {
                        response.append("Data has been Updated.");
                    } else {
                        throw new CommonException("Product Not Found");
                    }
                } else {
                    throw new CommonException("No Data Update");
                }
            } else {
                throw new CommonException("You are not Authorized!!");
            }

            responseInfo.setSuccess(response.toString());
        } catch (Exception e) {
            responseInfo.setException(e);
        }
        return responseInfo;
    }

    public ResponseInfo deleteProduct(int idUser, String productCode) {
        GenericRs body = new GenericRs();
        ResponseInfo responseInfo = new ResponseInfo().setBody(body);
        String response = "";

        try {
            if (userService.getAccessLevel(idUser)) {
                int i = productService.deleteProduct(idUser, productCode);
                if (i > 0) {
                    response = "Data has been Updated.";
                } else {
                    throw new CommonException("Product Not Found");
                }
            } else {
                throw new CommonException("You are not Authorized!!");
            }
            responseInfo.setSuccess(response);
        } catch (Exception e) {
            responseInfo.setException(e);
        }
        return responseInfo;
    }

    public ResponseInfo getProduct(String productCode, String productName, String type, int idUser) {
        GenericRs body = new GenericRs();
        ResponseInfo responseInfo = new ResponseInfo().setBody(body);
        String response = "";
        boolean supplierFlag = false;

        try {

            if (userService.getAccessLevel(idUser)) {
                supplierFlag = true;
            }
            List<ProductRef> listProduct = productService.getListProduct(productCode, productName, type, supplierFlag, idUser);

            if (listProduct != null && listProduct.size() > 0) {
                responseInfo.setSuccess(response);
            } else {
                throw new CommonException("No Product Found.");
            }

        } catch (Exception e) {
            responseInfo.setException(e);
        }
        return responseInfo;
    }

}
