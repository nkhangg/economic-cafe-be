package economic.main.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import economic.main.model.LikesComments;

public interface LikesCommentsReponsitory extends JpaRepository<LikesComments, Integer>{
    
}
