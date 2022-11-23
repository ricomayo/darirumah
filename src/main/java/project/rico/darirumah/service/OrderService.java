package project.rico.darirumah.service;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import project.rico.darirumah.config.AppConstant;
import project.rico.darirumah.config.AppProperties;
import project.rico.darirumah.model.Checkpoint;
import project.rico.darirumah.model.request.EndOrderRq;
import project.rico.darirumah.model.request.OrderRq;
import project.rico.darirumah.repository.OrderRepository;
import project.rico.darirumah.repository.RedisRepository;
import project.rico.darirumah.util.StringTools;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class OrderService {
    @Autowired
    @Qualifier(AppConstant.BEAN_APP_CONFIG)
    private AppProperties appProperties;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RedisRepository redisRepository;

    public String placeOrder(OrderRq orderRq){
        return orderRepository.placeOrder(orderRq.getIdUser(),orderRq.getProductCode(), orderRq.getQty(),orderRq.getDestinationName(),orderRq.getDestinationAddress());
    };

    public String finishOrder(EndOrderRq endOrderRq){
        return orderRepository.finishOrder(endOrderRq.getIdorder(),endOrderRq.getIdUser(), endOrderRq.getIdproduct());
    };

    public String cancelOrder(EndOrderRq endOrderRq){
        return orderRepository.cancelOrder(endOrderRq.getIdorder(),endOrderRq.getIdUser(), endOrderRq.getIdproduct());
    };


    public String getRedisKey(int idUser, int idOrder, String productCode ) {
        Checkpoint checkpoint;
        String token;

        String uniqueCode;
        do {
            uniqueCode = RandomStringUtils.random(
                    appProperties.getRANDOM_CODE_LENGTH(),
                    appProperties.isCHAR_RANDOM(),
                    appProperties.isNUMERIC_RANDOM());
            token = appProperties.getFORMAT_KEY().replace("%IDORDER%", String.valueOf(idOrder)).replace("%KEY%", uniqueCode);
            /*TODO CHECK KEY ON REDIS */
            log.info("[REDIS][{}:{}]", "CHECK TOKEN IN REDIS", token);
            checkpoint = redisRepository.findKey(token);
        } while (checkpoint != null);

        log.info("[REDIS][{}:{}]", "NEW TOKEN TO USE", token);

        Map<String, String> data = new HashMap<>();
        data.put("idUser", String.valueOf(idUser));
        data.put("idOrder", String.valueOf(idOrder));
        data.put("idProduct", productCode);
        checkpoint = new Checkpoint()
                .setKey(token)
                .setTimeToLive(appProperties.getREDIS_TOKEN_TTL())
                .setValue(data);
        checkpoint.now();
        redisRepository.saveKey(checkpoint);

        log.info("[REDIS][{}:{}]", "SUCCESS SAVE TOKEN IN REDIS", StringTools.gson.toJson(checkpoint));

        return uniqueCode;
    }

}
