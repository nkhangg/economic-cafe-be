package economic.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import economic.main.constants.MessageResponse;
import economic.main.model.Category;
import economic.main.payload.respone.ApiResponce;
import economic.main.reponsitory.CategoryReponsitory;
import economic.main.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @Autowired
    // private PostCategoryReponsitory postCategoryReponsitory;

    @GetMapping("")
    public Object get(HttpServletRequest request ) {
       
        List<Category> list = categoryService.getCategoriesHome(request);
        return list;
    }

    @GetMapping("/{idCategory}/{nameCategory}")
    public ResponseEntity<ApiResponce> getDetailCategory(@PathVariable int idCategory, @PathVariable String nameCategory){
        ApiResponce responce = categoryService.getPostCategory(idCategory, nameCategory);

        if(responce == null){
            return ResponseEntity.badRequest().body(ApiResponce.builder()
            .message(MessageResponse.FAILURE.getValue())
            .errors(true)
            .data(null)
            .status(HttpStatus.NOT_FOUND.value())
            .build());
        }

        return ResponseEntity.ok(categoryService.getPostCategory(idCategory, nameCategory));
    }
}
