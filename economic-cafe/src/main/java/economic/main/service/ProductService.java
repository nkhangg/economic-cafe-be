package economic.main.service;

import java.util.List;

import economic.main.model.Product;
import jakarta.servlet.http.HttpServletRequest;

public interface ProductService {
List<Product> getProductsHome(HttpServletRequest http);
}
