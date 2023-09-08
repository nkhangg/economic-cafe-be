package economic.main.model;


import java.util.Date;

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

	@Column(name="created_at")
	private Date createdAt = new Date();

	private boolean deleted = false;

    @Column(name = "image")
	private String image;

	@Column(name="updated_at")
	private Date updatedAt = new Date();

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="id_product", nullable = false, referencedColumnName = "id")
	private Product product;
}
