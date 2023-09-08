package economic.main.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import economic.main.model.ShippingUnit;

public interface ShippingUnitReponsitory extends JpaRepository<ShippingUnit, Integer>{
    
}
