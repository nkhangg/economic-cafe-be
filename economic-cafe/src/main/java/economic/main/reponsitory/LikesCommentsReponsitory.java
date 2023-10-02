package economic.main.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import economic.main.model.Comment;
import economic.main.model.LikesComments;

public interface LikesCommentsReponsitory extends JpaRepository<LikesComments, Integer>{

    @Query("select l from LikesComments l where l.state = true and l.comment = :comment")
    List<LikesComments> findByComment(@Param("comment") Comment comment);

    

    
}
