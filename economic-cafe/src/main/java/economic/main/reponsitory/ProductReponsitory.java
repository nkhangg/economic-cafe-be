package economic.main.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import economic.main.model.Product;

public interface ProductReponsitory extends JpaRepository<Product, String>{

    @Query("select p from Product p where p.hot = true order by p.discount desc")
    List<Product> findProductsHotHomepage();
}
