package economic.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import economic.main.constants.MessageResponse;
import economic.main.model.Category;
import economic.main.model.Product;
import economic.main.payload.respone.ApiResponce;
import economic.main.payload.respone.HomepageResponce;
import economic.main.service.AppPageService;
import economic.main.service.CategoryService;
import economic.main.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class AppPageServiceImpl implements AppPageService{

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Override
    public ApiResponce homePage(HttpServletRequest request) {
        List<Category> categories = categoryService.getCategoriesHome(request);
        List<Product> products = productService.getProductsHome(request);

        // check categories, products is empty
        if(categories.isEmpty() || products.isEmpty()){
            return ApiResponce.builder()
            .message(MessageResponse.FAILURE.getValue())
            .status(HttpStatus.BAD_REQUEST.value())
            .errors(true)
            .data(null)
            .build();
        }

        // return data to client
        return ApiResponce.builder()
        .message(MessageResponse.SUCCESS.getValue())
        .status(HttpStatus.OK.value())
        .errors(false)
        .data(HomepageResponce.builder()
        .categories(categories)
        .products(products)
        .build())
        .build();
    }
    
}
