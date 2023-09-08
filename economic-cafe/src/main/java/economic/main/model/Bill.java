package economic.main.model;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    @ManyToOne
	@JoinColumn(name="id_user", nullable = false, referencedColumnName = "id")
	private User user;

	private String description;

	@Column(name="done_date")
	private Date doneDate;

	private int status;

    @Column(name="created_at")
	private Date createdAt = new Date();

    @Column(name="update_at")
	private Date updateAt = new Date();

	@ManyToOne
	@JoinColumn(name="id_payments", nullable = false, referencedColumnName = "id")
	private Payment payment;

	@ManyToOne
	@JoinColumn(name="id_receiver", nullable = false, referencedColumnName = "id")
	private Receiver receiver;

	@ManyToOne
	@JoinColumn(name="id_shipping_unit", nullable = false, referencedColumnName = "id")
	private ShippingUnit shippingUnit;

	@OneToMany(mappedBy = "bill")
	private Set<DetailBill> detailBills;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name="Bill_Discounts"
		, joinColumns={
			@JoinColumn(name="id_bill")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_discount")
			}
		)
	private Set<Discount> discounts;
	
	
}
