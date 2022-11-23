package project.rico.darirumah.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.rico.darirumah.model.ResponseInfo;
import project.rico.darirumah.model.request.EndOrderRq;
import project.rico.darirumah.model.request.OrderRq;
import project.rico.darirumah.model.response.GenericRs;
import project.rico.darirumah.service.OrderService;

@Slf4j
@Component
public class PurchaseUsecase extends BaseUsecase{
    @Autowired
    OrderService orderService;

   public ResponseInfo placeOrder(OrderRq orderRq){

       GenericRs body = new GenericRs();
       ResponseInfo responseInfo = new ResponseInfo().setBody(body);
       String response = "";

       try {
            response = orderService.placeOrder(orderRq);

           responseInfo.setSuccess(response);
       } catch (Exception e) {
           responseInfo.setException(e);
       }
       return responseInfo;
   }

    public ResponseInfo finishOrder(EndOrderRq finishOrderRq){

        GenericRs body = new GenericRs();
        ResponseInfo responseInfo = new ResponseInfo().setBody(body);
        String response = "";

        try {
            response = orderService.finishOrder(finishOrderRq);

            responseInfo.setSuccess(response);
        } catch (Exception e) {
            responseInfo.setException(e);
        }
        return responseInfo;
    }

}
