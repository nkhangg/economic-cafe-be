package economic.main.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Transport_Fee")
public class TransportFee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	

	@Column(name="km_max")
	private double kmMax;

    @Column(name="weight_max")
	private double weightMax;

	private int price;

    private boolean deleted = false;

    @Column(name="created_at")
	private Date createdAt = new Date();

	@Column(name="updated_at")
	private Date updatedAt = new Date();


	//bi-directional many-to-one association to Shipping_Unit
	@ManyToOne
	@JoinColumn(name="id_shipping_unit", nullable = false, referencedColumnName = "id")
	private ShippingUnit shippingUnit;
}
