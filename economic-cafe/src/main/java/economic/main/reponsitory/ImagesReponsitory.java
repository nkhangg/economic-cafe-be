package economic.main.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import economic.main.model.Image;
import economic.main.model.Product;

public interface ImagesReponsitory extends JpaRepository<Image, Integer>{
    List<Image> findByProduct(Product product);
}
