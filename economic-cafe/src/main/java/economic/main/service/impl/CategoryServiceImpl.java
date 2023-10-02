package economic.main.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import economic.main.constants.MessageResponse;
import economic.main.constants.TypeFileImage;
import economic.main.model.Category;
import economic.main.model.PostCategory;
import economic.main.payload.respone.ApiResponce;
import economic.main.reponsitory.CategoryReponsitory;
import economic.main.reponsitory.PostCategoryReponsitory;
import economic.main.service.CategoryService;
import economic.main.ultils.AppUltil;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryReponsitory categoryReponsitory;

    @Autowired
    private PostCategoryReponsitory postCategoryReponsitory;


    @Autowired
    private AppUltil appUltil;

    @Override
    public List<Category> getCategoriesHome(HttpServletRequest http) {

        List<Category> categories = categoryReponsitory.findCategoriesHome().stream().map((category) -> {

            category.setThumbnail(appUltil.getUrlImage(category.getThumbnail(), TypeFileImage.CATEGORIES));

            return category;
        }).toList();

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
