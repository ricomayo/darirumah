package project.rico.darirumah.controller.solace;

import com.solacesystems.common.util.CommonUtils;
import com.solacesystems.jcsmp.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import project.rico.darirumah.config.AppConstant;
import project.rico.darirumah.config.AppProperties;
import project.rico.darirumah.model.ResponseInfo;
import project.rico.darirumah.model.request.EndOrderRq;
import project.rico.darirumah.model.response.GenericRs;
import project.rico.darirumah.model.solace.CommonBean;
import project.rico.darirumah.model.solace.SolaceMessageRq;
import project.rico.darirumah.usecase.OrderUsecase;
import project.rico.darirumah.util.StringTools;

@Slf4j
@Component
public class SolaceReceiver implements XMLMessageListener {
    private final AppProperties appProperties;
    private final QueueSubscriber queueSubscriber;
    private final CommonBean commonBean;

    private final OrderUsecase orderUsecase;


    public SolaceReceiver(
            @Qualifier(AppConstant.BEAN_APP_CONFIG) AppProperties appProperties
            , QueueSubscriber queueSubscriber
            , CommonBean commonBean
            , @Autowired OrderUsecase orderUsecase

    ) throws JCSMPException {
        this.appProperties = appProperties;
        this.queueSubscriber = queueSubscriber;
        this.commonBean = commonBean;
        this.orderUsecase = orderUsecase;


        log.info("[-----Starting Solace session-----]");
        ConsumerFlowProperties flowProperties = new ConsumerFlowProperties();
        flowProperties.setAckMode(JCSMPProperties.SUPPORTED_MESSAGE_ACK_AUTO);
        queueSubscriber.queueListener(appProperties.getSOLACE_SMS_QUEUE_DESTINATION(), flowProperties, this);

    }

    // receive queue
    @Override
    public void onReceive(BytesXMLMessage msg) {
        if (msg instanceof TextMessage) {
            String inputMsg = ((TextMessage) msg).getText();
            log.debug("[-----TextMessage received: '{}', from: '{}'-----]", inputMsg.replaceAll("\\s*[\\r\\n]+\\s*", "").trim(), msg.getDestination().getName());
            processMessage(inputMsg);

        } else {
            String inputMsg = new String(msg.getBytes());
            log.debug("[-----Message received.'{}', from: '{}'-----]", inputMsg.replaceAll("\\s*[\\r\\n]+\\s*", "").trim(), msg.getDestination().getName());
            processMessage(inputMsg);
        }
    }

    public void processMessage(String textMessage) {

        try {

            SolaceMessageRq solaceMessageRq = StringTools.gson.fromJson(textMessage, SolaceMessageRq.class);
            String eventId = solaceMessageRq.getEventId();
            String idOrder = solaceMessageRq.getIdOrder();
            String idProduct = solaceMessageRq.getIdProduct();
            String idUser = solaceMessageRq.getIdUser();
            String status = solaceMessageRq.getStatus();
            String timestamp = solaceMessageRq.getTimestamp();

            log.info("[SOLACE GET MESSAGE][eventId={} - idOrder={} - idProduct={} - idUser={} - status={} - timestamp={}]", eventId, idOrder, idProduct, idUser, status, timestamp);


            if(StringTools.equals(status,AppConstant.ORDER_PRODUCT.SUCCESS.toString())){
                EndOrderRq endOrderRq = new EndOrderRq();
                endOrderRq.setIdorder(Integer.parseInt(idOrder));
                endOrderRq.setIdproduct(Integer.parseInt(idProduct));
                endOrderRq.setIdUser(Integer.parseInt(idUser));


                GenericRs body = new GenericRs();
                ResponseInfo responseInfo = new ResponseInfo().setBody(body);

                responseInfo = orderUsecase.finishOrder(endOrderRq);

                log.info("[SOLACE DONE][eventId={} - idOrder={} - idProduct={} - idUser={} - status={} - timestamp={}]", eventId, idOrder, idProduct, idUser, status, timestamp);

            }


        } catch (Exception e) {
            log.error("[MESSAGE DUMP -> {}", textMessage);
            log.error("[ORIGINAL REQUEST -> {}", textMessage.replaceAll("\\s++", ""));
            log.error("[FAILED PARSE PAYLOAD -> {} | {}]", e.getMessage(), e);
        }
    }

    @Override
    public void onException(JCSMPException e) {
        System.out.printf("[-----Consumer received exception: %s%n-----]", e);
    }


}