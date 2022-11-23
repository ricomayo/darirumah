package project.rico.darirumah.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import project.rico.darirumah.config.AppConstant;
import project.rico.darirumah.config.AppProperties;
import project.rico.darirumah.model.request.EndOrderRq;
import project.rico.darirumah.model.request.OrderRq;
import project.rico.darirumah.repository.OrderRepository;

@Slf4j
@Service
public class OrderService {
    @Autowired
    @Qualifier(AppConstant.BEAN_APP_CONFIG)
    private AppProperties appProperties;

    @Autowired
    OrderRepository orderRepository;

    public String placeOrder(OrderRq orderRq){
        return orderRepository.placeOrder(orderRq.getIdUser(),orderRq.getProductCode(), orderRq.getQty(),orderRq.getDestinationName(),orderRq.getDestinationAddress());
    };

    public String finishOrder(EndOrderRq endOrderRq){
        return orderRepository.finishOrder(endOrderRq.getIdorder(),endOrderRq.getIdUser(), endOrderRq.getIdproduct());
    };

    public String cancelOrder(EndOrderRq endOrderRq){
        return orderRepository.cancelOrder(endOrderRq.getIdorder(),endOrderRq.getIdUser(), endOrderRq.getIdproduct());
    };


}
