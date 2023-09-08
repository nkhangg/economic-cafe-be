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
@Table(name = "Discounts")
public class Discount {
    @Id
	private String id;

    private String name;

	private int condition;

    @Column(name="begin_date")
	private Date beginDate = new Date();

	@Column(name="close_date")
	private Date closeDate;

	private int discount;

    @Column(name="use_num")
	private int useNum;
    
    private boolean deleted = false;

    @Column(name="created_at")
	private Date createdAt = new Date();

	@Column(name="updated_at")
	private Date updatedAt = new Date();

	

	//bi-directional many-to-many association to Bill
	@ManyToMany(mappedBy="discounts")
	private Set<Bill> bills;
}
