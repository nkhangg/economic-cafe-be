package economic.main.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import economic.main.model.Receiver;

public interface ReceiverReponsitory extends JpaRepository<Receiver, Integer>{
    
}
