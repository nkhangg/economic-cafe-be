package economic.main.model;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Images")
public class Image {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonIgnore
	private boolean deleted = false;

    @Column(name = "image")
	private String image;

	@Column(name="updated_at")
	@JsonFormat(pattern="dd/MM/yyy HH:mm:ss")
	private Date updatedAt = new Date();

	@Column(name="created_at")
	@JsonFormat(pattern="dd/MM/yyy HH:mm:ss")
	private Date createdAt = new Date();

	//bi-directional many-to-one association to Product
	@JsonIgnore
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="id_product", nullable = false, referencedColumnName = "id")
	private Product product;
}
