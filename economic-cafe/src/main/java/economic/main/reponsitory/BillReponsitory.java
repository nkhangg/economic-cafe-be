package economic.main.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import economic.main.model.Bill;

public interface BillReponsitory extends JpaRepository<Bill, Integer>{
    
}
