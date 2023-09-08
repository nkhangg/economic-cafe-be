package economic.main.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Category_Post")
public class PostCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_category", referencedColumnName = "id")
    private Category category;

    private String thumbnail ;

    private String contents ;

    private boolean deleted  = false;

    @JsonFormat(pattern="dd/MM/yyy HH:mm:ss")
    @Column(name="created_at")
	private Date createdAt = new Date();
    
    @JsonFormat(pattern="dd/MM/yyy HH:mm:ss")
	@Column(name="updated_at")
	private Date updatedAt = new Date();

}
