package project.rico.darirumah.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.rico.darirumah.config.AppConstant;
import project.rico.darirumah.exception.definition.CommonException;
import project.rico.darirumah.model.ResponseInfo;
import project.rico.darirumah.model.db.ProductRef;
import project.rico.darirumah.model.db.StockRef;
import project.rico.darirumah.model.db.UserRef;
import project.rico.darirumah.model.request.ModifyProductRq;
import project.rico.darirumah.model.response.GenericRs;
import project.rico.darirumah.service.StockService;
import project.rico.darirumah.service.UserService;
import project.rico.darirumah.util.StringTools;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component

public class StockUsecase {
    @Autowired
    StockService stockService;

    @Autowired
    UserService userService;

    public ResponseInfo addNewStock(int idUser, String productCode, int qty) {
        GenericRs body = new GenericRs();
        ResponseInfo responseInfo = new ResponseInfo().setBody(body);
        String response = "";

        try {
            if (userService.getAccess(idUser)) {

                List<StockRef> listStockRef = stockService.getListStock(productCode, null, null);

                StockRef stockRef = listStockRef.stream()
                        .filter(d -> StringTools.equals(d.getProductCode(), productCode)
                        ).findAny().orElse(null);

                if (stockRef == null) {
                    response = stockService.addStock(idUser, productCode, qty);
                } else {
                    int i = stockService.updateStock(idUser, Integer.parseInt(stockRef.getIdStock()), Integer.parseInt(stockRef.getIdProduct()), qty);
                    if (i > 0) {
                        response = "SUCCESS";
                    }
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

    public ResponseInfo updateStock(int idUser, int idStock, int idProduct, int qty) {
        GenericRs body = new GenericRs();
        ResponseInfo responseInfo = new ResponseInfo().setBody(body);
        StringBuilder response = new StringBuilder();
        try {
            if (userService.getAccess(idUser)) {
                int i = stockService.updateStock(idUser, idStock, idProduct, qty);
                if (i > 0) {
                    response.append("Stock has been Updated.");
                } else {
                    throw new CommonException("Product Not Found");
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

    public ResponseInfo deleteStock() {
        GenericRs body = new GenericRs();
        ResponseInfo responseInfo = new ResponseInfo().setBody(body);
        String response = "";

        try {
            int i = stockService.deleteStock();
            responseInfo.setSuccess();
        } catch (Exception e) {
            responseInfo.setException(e);
        }
        return responseInfo;
    }

    public ResponseInfo getStock(String productCode, String productName, String type) {
        GenericRs body = new GenericRs();
        ResponseInfo responseInfo = new ResponseInfo().setBody(body);
        String response = "";

        try {
            List<StockRef> listStockRef = stockService.getListStock(productCode, productName, type);
            if (listStockRef != null && listStockRef.size() > 0) {
                responseInfo.setSuccess(listStockRef);
            } else {
                throw new CommonException("No Product Found.");
            }

        } catch (Exception e) {
            responseInfo.setException(e);
        }
        return responseInfo;
    }


}
