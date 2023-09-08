package economic.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import economic.main.model.Product;
import economic.main.reponsitory.ProductReponsitory;

@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private ProductReponsitory prRep;

    @GetMapping("")
    public List<Product> selectAll() {
        return prRep.findProductsHotHomepage();
    }
}
