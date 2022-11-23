package project.rico.darirumah.controller.solace;


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
public class SolaceReceiver {

}