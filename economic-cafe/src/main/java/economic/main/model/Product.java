package economic.main.model;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Product {
   	@Id
	private String id;

	private String name;

	private String conserve;

	private String description;

	private int discount;

	private String flavor;

	private boolean hot;

	private String image;

	private String ingredient;

	@Column(name="expiry_date")
	private String expiryDate;

	@Column(name="user_nanual")
	private String userNanual;

	@JsonIgnore
	private boolean deleted;

	@JsonFormat(pattern="dd/MM/yyy HH:mm:ss")
	@Column(name="updated_at")
	private Date updatedAt;

	@JsonFormat(pattern="dd/MM/yyy HH:mm:ss")
	@Column(name="created_at")
	private Date createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_category", nullable = false, referencedColumnName = "id")
	@JsonManagedReference
    private Category category;

	@OneToMany(mappedBy = "product")
	private Set<Inventory> inventories;

	@JsonIgnore
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set<Image> images;

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private Set<DetailBill> detailBills;

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private Set<Comment> comments;

	// public String getImage(){
	// 	return ImageUntils.getURLImage(this.image, TypeFileImage.PRODUCT);
	// }

	
}
