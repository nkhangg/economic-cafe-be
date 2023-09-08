package economic.main.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import economic.main.model.Payment;

public interface PaymentReponsitory extends JpaRepository<Payment, Integer> {
    
}
