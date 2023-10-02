package economic.main.service;

import java.util.List;

import economic.main.model.Product;
import economic.main.payload.respone.ApiResponce;
import economic.main.payload.respone.modal.ProductResponce;
import jakarta.servlet.http.HttpServletRequest;

public interface ProductService {
    List<ProductResponce> getProductsHome(HttpServletRequest http); 
    ApiResponce getDetailProduct(String id, String name, String username);

    ProductResponce buildProduct(Product product, boolean buildImageCategory);
}
