package economic.main.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import economic.main.model.Category;
import economic.main.model.PostCategory;


public interface PostCategoryReponsitory extends JpaRepository<PostCategory, Integer>{
    List<PostCategory> findByCategory(Category category);
}
