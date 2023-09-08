package economic.main.model;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Comments")
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String content;

	@Column(name="rep_id")
	private int repId = 0;

    @Column(name="likes")
	private int likes = 0;

    private boolean deleted = false;

    @Column(name="created_at")
	@JsonFormat(pattern="dd/MM/yyy HH:mm:ss")
	private Date createdAt = new Date();

	@Column(name="updated_at")
	@JsonFormat(pattern="dd/MM/yyy HH:mm:ss")
	private Date updatedAt = new Date();

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="id_product")
	private Product product;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;

	@OneToMany(mappedBy = "comment")
	private Set<LikesComments> likesComments;
	
}
