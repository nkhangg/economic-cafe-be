package economic.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import economic.main.model.Product;
import economic.main.payload.request.CommentsGetLikeResquest;
import economic.main.payload.respone.ApiResponce;
import economic.main.reponsitory.ProductReponsitory;
import economic.main.service.ProductService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    
    @Autowired
    private ProductReponsitory prRep;

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public List<Product> selectAll() {
        return prRep.findProductsHotHomepage();
    }
    @GetMapping("/{id}/{name}")
    public ResponseEntity<ApiResponce> getDetailProduct(@PathVariable String id, @PathVariable String name, @RequestBody CommentsGetLikeResquest commentsGetLikeResquest ) {


        System.out.println(id);
        ApiResponce apiResponce = productService.getDetailProduct(id, name, commentsGetLikeResquest.getUsername());
        
        return ResponseEntity.ok(apiResponce);
    }


}
