package economic.main.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import economic.main.model.Discount;

public interface DiscountsReponsitory extends JpaRepository<Discount, String>{
    
}
