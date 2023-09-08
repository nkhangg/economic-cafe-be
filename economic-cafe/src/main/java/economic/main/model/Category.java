package economic.main.model;

import java.util.Date;
import java.util.Set;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String description;

	private String thumbnail;

	@JsonIgnore
	@Column(name="show_on")
	private boolean showOn = false;

	// @OneToOne(mappedBy = "category")
	// @JsonIgnore
	// private PostCategory postCategory;

	@JsonIgnore
    @Column(name = "deleted")
	private boolean deleted = false;

	@JsonIgnore
	@OneToMany(mappedBy="category", fetch = FetchType.EAGER)
    @JsonBackReference
	private Set<Product> products;

	// public String getThumbnail(){
	// 	return ImageUntils.getURLImage(this.thumbnail, TypeFileImage.CATEGORIES);
	// }
}
