package economic.main.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import economic.main.model.Comment;

public interface CommentReponsitory extends JpaRepository<Comment, Integer>{
    
}
