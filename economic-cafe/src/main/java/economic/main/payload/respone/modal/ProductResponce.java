package economic.main.payload.respone.modal;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import economic.main.model.Category;
import economic.main.model.Inventory;
import economic.main.model.Product;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponce {

    // private Category category;

    public ProductResponce(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.conserve = product.getConserve();
        this.description = product.getDescription();
        this.discount = product.getDiscount();
        this.flavor = product.getFlavor();
        this.hot = product.isHot();
        this.deleted = product.isDeleted();
        this.createdAt = product.getCreatedAt();
        this.updatedAt = product.getUpdatedAt();
        this.image = product.getImage();
        this.ingredient = product.getIngredient();
        this.expiryDate = product.getExpiryDate();
        this.userNanual = product.getUserNanual();
        this.category = product.getCategory();
    }

	private String id;

	private String name;

    private double newPrice;

    private double oldPrice;

	private String conserve;

	private String description;

	private int discount;

	private String flavor;

	private boolean hot;

	private String image;

	private String ingredient;

	private String expiryDate;

	private String userNanual;

	@JsonIgnore
	private boolean deleted;

	@JsonFormat(pattern="dd/MM/yyy HH:mm:ss")
	private Date updatedAt;

	@JsonFormat(pattern="dd/MM/yyy HH:mm:ss")
	private Date createdAt;

    List<Inventory> inventories;

    private Category category;
}
