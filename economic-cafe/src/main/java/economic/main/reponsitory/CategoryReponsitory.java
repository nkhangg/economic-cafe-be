package economic.main.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import economic.main.model.Category;

public interface CategoryReponsitory extends JpaRepository<Category, Integer>{

    public List<Category> findByName(String name);


    @Query("select c from Category c where c.showOn = true and c.deleted = false")
    public List<Category> findCategoriesHome();

    public boolean existsByName(String name); 
    
}
