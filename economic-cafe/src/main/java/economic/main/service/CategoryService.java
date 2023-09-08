package economic.main.service;

import java.util.List;

import economic.main.model.Category;
import economic.main.payload.respone.ApiResponce;
import jakarta.servlet.http.HttpServletRequest;

public interface CategoryService {
    List<Category> getCategoriesHome(HttpServletRequest http);

    ApiResponce getPostCategory(int idCategory, String name);
}
