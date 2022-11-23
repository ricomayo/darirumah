package project.rico.darirumah.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import project.rico.darirumah.config.AppConstant;
import project.rico.darirumah.config.AppProperties;
import project.rico.darirumah.model.db.StockRef;
import project.rico.darirumah.repository.StockRepository;

import java.util.List;

@Slf4j
@Service
public class StockService {
    @Autowired
    @Qualifier(AppConstant.BEAN_APP_CONFIG)
    private AppProperties appProperties;

    @Autowired
    StockRepository stockRepository;

    public List<StockRef> getListStock(String productCode, String productName, String type) {
        return stockRepository.getStock(productCode, productName, type);
    }

    public String addStock(int idUser, String productCode, int qty) {

        return stockRepository.insertStock(idUser, productCode, qty);
    }

    public int updateStock(int idUser, int idStock, int idProduct, int qty) {

        return stockRepository.updateStock(idUser, idStock, idProduct, qty);
    }

    public int deleteStock() {

        return stockRepository.deleteStock();
    }
}
