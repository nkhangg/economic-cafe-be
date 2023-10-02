package economic.main.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import economic.main.model.Product;

public interface ProductReponsitory extends JpaRepository<Product, String>{

    @Query("select p from Product p where p.hot = true and deleted = false order by p.discount desc")
    List<Product> findProductsHotHomepage();

    @Query("select p from Product p where p.id = :id and p.name = :name and deleted = false")
    Product findProductIsUsing(@Param("id") String id, @Param("name") String name);

    List<Product> findByName(String name);

    boolean existsById(String id);
    boolean existsByName(String name);
}
