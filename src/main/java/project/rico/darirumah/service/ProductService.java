package project.rico.darirumah.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import project.rico.darirumah.config.AppConstant;
import project.rico.darirumah.config.AppProperties;
import project.rico.darirumah.model.db.ProductRef;
import project.rico.darirumah.model.request.ModifyProductRq;
import project.rico.darirumah.repository.ProductRepository;

import java.util.List;

@Slf4j
@Service
public class ProductService {
    @Autowired
    @Qualifier(AppConstant.BEAN_APP_CONFIG)
    private AppProperties appProperties;

    @Autowired
    private ProductRepository productRepository;

    public List<ProductRef> getListProduct(String productCode, String productName, String type) {
        return productRepository.getProduct(productCode, productName, type);
    }

    public String addProduct(int idUser, ModifyProductRq modifyProductRq) {

        return productRepository.insertProduct(idUser, modifyProductRq.getProductCode(), modifyProductRq.getProductName(), modifyProductRq.getUom(), modifyProductRq.getType());
    }

    public int updateProduct(int idUser, String productCode, ModifyProductRq modifyProductRq) {

        return productRepository.updateProduct(idUser, productCode, modifyProductRq.getProductName(), modifyProductRq.getUom(), modifyProductRq.getType());
    }

    public int deleteProduct(int idUser, String productCode) {

        return productRepository.deleteProduct(idUser, productCode);
    }
}
