package economic.main.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import economic.main.model.Comment;
import economic.main.model.Product;

public interface CommentReponsitory extends JpaRepository<Comment, Integer>{

    @Query("select c from Comment c where c.product = :product and c.deleted = false and c.repId = 0")
    List<Comment> findByProduct(Product product);

    List<Comment> findByRepId(int repId);
}
