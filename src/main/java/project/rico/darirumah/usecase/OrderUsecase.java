package project.rico.darirumah.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.rico.darirumah.config.AppConstant;
import project.rico.darirumah.exception.definition.CommonException;
import project.rico.darirumah.model.ResponseInfo;
import project.rico.darirumah.model.request.EndOrderRq;
import project.rico.darirumah.model.request.OrderRq;
import project.rico.darirumah.model.response.GenericRs;
import project.rico.darirumah.service.OrderService;
import project.rico.darirumah.util.StringTools;

@Slf4j
@Component
public class OrderUsecase extends BaseUsecase{
    @Autowired
    OrderService orderService;

   public ResponseInfo placeOrder(OrderRq orderRq){

       GenericRs body = new GenericRs();
       ResponseInfo responseInfo = new ResponseInfo().setBody(body);

       try {
            String result = orderService.placeOrder(orderRq);

            if(!StringTools.equals(result, AppConstant.ORDER_PRODUCT.SUCCESS.toString())){
                throw new CommonException(result);
            }

            String redisKey  = orderService.getRedisKey(orderRq.getIdUser(),Integer.parseInt(result), orderRq.getProductCode());

           responseInfo.setSuccess("SUCCESS with key= "+redisKey);
       } catch (Exception e) {
           responseInfo.setException(e);
       }
       return responseInfo;
   }

    public ResponseInfo finishOrder(EndOrderRq endOrderRq){

        GenericRs body = new GenericRs();
        ResponseInfo responseInfo = new ResponseInfo().setBody(body);
        String response = "";

        try {
            response = orderService.finishOrder(endOrderRq);

            if(!StringTools.equals(response, AppConstant.ORDER_PRODUCT.SUCCESS.toString())){
                throw new CommonException(response);
            }

            orderService.deleteRedisKey(endOrderRq.getToken());
            responseInfo.setSuccess(response);
        } catch (Exception e) {
            responseInfo.setException(e);
        }
        return responseInfo;
    }

    public ResponseInfo cancelOrder(EndOrderRq endOrderRq){

        GenericRs body = new GenericRs();
        ResponseInfo responseInfo = new ResponseInfo().setBody(body);
        String response = "";

        try {
            response = orderService.cancelOrder(endOrderRq);

            responseInfo.setSuccess(response);
        } catch (Exception e) {
            responseInfo.setException(e);
        }
        return responseInfo;
    }

}
