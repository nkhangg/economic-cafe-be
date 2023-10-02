package economic.main.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import economic.main.model.Inventory;
import economic.main.model.Product;

import java.util.List;


public interface InventoryRepository extends JpaRepository<Inventory, Integer>{
    List<Inventory> findByProduct(Product product);

    @Query("select i from Inventory i where i.product = :product order by i.price asc")
    List<Inventory> findMinByProduct(@Param("product") Product product);
}
