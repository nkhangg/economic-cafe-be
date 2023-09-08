package economic.main.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import economic.main.model.Image;

public interface ImagesReponsitory extends JpaRepository<Image, Integer>{
    
}
