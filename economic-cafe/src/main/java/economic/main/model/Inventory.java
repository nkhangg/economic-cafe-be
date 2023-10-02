package economic.main.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_type")
    private String nameType;

    private String weight;

    private int price;

    @JsonIgnore
    @ManyToOne
	@JoinColumn(name="id_product", nullable = false, referencedColumnName = "id")
    private Product product;

    private int inventory;

    private boolean deleted = false;

    @Column(name="created_at")
    @JsonFormat(pattern="dd/MM/yyy HH:mm:ss")
	private Date createdAt = new Date();

	@Column(name="updated_at")
    @JsonFormat(pattern="dd/MM/yyy HH:mm:ss")
	private Date updatedAt = new Date();
}
