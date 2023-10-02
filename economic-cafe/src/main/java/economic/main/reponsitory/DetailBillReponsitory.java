package economic.main.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import economic.main.model.DetailBill;
import economic.main.model.Product;

public interface DetailBillReponsitory extends JpaRepository<DetailBill, Integer>{
    List<DetailBill> findByProduct(Product product);
}
