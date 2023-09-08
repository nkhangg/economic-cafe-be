package economic.main.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import economic.main.model.DetailBill;

public interface DetailBillReponsitory extends JpaRepository<DetailBill, Integer>{
    
}
