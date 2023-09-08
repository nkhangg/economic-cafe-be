package economic.main.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import economic.main.constants.TypeFileImage;
import economic.main.model.Product;
import economic.main.reponsitory.ProductReponsitory;
import economic.main.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;


@Service
public class ProductServiceImpl implements ProductService{


    @Autowired
    ProductReponsitory productReponsitory;

    @Override
    public List<Product> getProductsHome(HttpServletRequest http) {

        String domain = http.getHeader("Host").contains("http://") ? http.getHeader("Host") : "http://" + http.getHeader("Host");

        String prefixImageUrl = domain + "/images/" + TypeFileImage.PRODUCT.value() + "/";
        List<Product> products = new ArrayList<>();
        productReponsitory.findProductsHotHomepage().stream().forEach((product) -> {
            product.setImage(prefixImageUrl + product.getImage());
            products.add(product);
        });

        return products;
        
    }
    
}
