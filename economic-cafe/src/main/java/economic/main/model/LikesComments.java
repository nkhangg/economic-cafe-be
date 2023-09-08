package economic.main.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Likes_Comments")
public class LikesComments {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
	@JoinColumn(name="id_user", nullable = false, referencedColumnName = "id")
    private User user;

    @ManyToOne
	@JoinColumn(name="id_comment", nullable = false, referencedColumnName = "id")
    private Comment comment;

    private boolean state = true;

    @Column(name="created_at")
	private Date createdAt = new Date();

	@Column(name="updated_at")
	private Date updatedAt = new Date();
}
