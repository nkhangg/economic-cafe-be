package economic.main.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import economic.main.constants.MessageResponse;
import economic.main.model.Category;
import economic.main.model.PostCategory;
import economic.main.payload.respone.ApiResponce;
import economic.main.reponsitory.CategoryReponsitory;
import economic.main.reponsitory.PostCategoryReponsitory;
import economic.main.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryReponsitory categoryReponsitory;

    @Autowired
    private PostCategoryReponsitory postCategoryReponsitory;

    @Override
    public List<Category> getCategoriesHome(HttpServletRequest http) {

        
        // String domain = http.getHeader("Host").contains("http://") ? http.getHeader("Host") : "http://" + http.getHeader("Host");

        // String prefixImageUrl = domain + "/images/" + TypeFileImage.CATEGORIES.value() + "/";

        List<Category> categories = new ArrayList<>();

           categoryReponsitory.findCategoriesHome().stream().forEach((category) -> {
                category.setThumbnail("http://localhost:8088/images/categories/" + category.getThumbnail());
                categories.add(category);
            });

        return categories;
    }

    @Override
    public ApiResponce getPostCategory(int idCategory, String name) {
        Category category = categoryReponsitory.findById(idCategory).orElse(null);


        if(category == null || !categoryReponsitory.existsByName(name.replace("-", " "))){
            return null;
        }

        PostCategory postCategory = postCategoryReponsitory.findByCategory(category).get(0);

        if(postCategory == null){
            return null;
        }
        return ApiResponce.builder()
        .message(MessageResponse.SUCCESS.getValue())
        .errors(false)
        .status(HttpStatus.OK.value())
        .data(postCategory)
        .build();

    }
    
}
