package economic.main.payload.respone.modal;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import economic.main.model.Comment;
import economic.main.model.Product;
import economic.main.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentResponce {
    

    public CommentResponce(Comment comment){
        this.id = comment.getId();
        this.content = comment.getContent();
        this.repId = comment.getRepId();
        this.product = comment.getProduct();
        this.user = comment.getUser();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();

        
    }

    private int id;

	private String content;

    private int repId;

	private int likes;

    private boolean liked = false;

    private User user;

    @JsonIgnore
    private Product product;

    private CommentResponce items = null;

	@JsonFormat(pattern="dd/MM/yyy HH:mm:ss")
	private Date createdAt;

	@JsonFormat(pattern="dd/MM/yyy HH:mm:ss")
	private Date updatedAt;
}
