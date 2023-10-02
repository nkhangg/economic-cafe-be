package economic.main.model;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@JsonIgnore
    private String name;

	private int condition;

	private int max;

	@JsonIgnore
    @Column(name="begin_date")
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss", timezone = "ICT")
	private Date beginDate = new Date();

	@Column(name="close_date")
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss", timezone = "ICT")
	private Date closeDate;

	private int discount;

	@JsonIgnore
    @Column(name="use_num")
	private int useNum;
    
	@JsonIgnore
    private boolean deleted = false;

	@JsonIgnore
    @Column(name="created_at")
	@JsonFormat(pattern="dd/MM/yyy HH:mm:ss")
	private Date createdAt = new Date();

	@JsonIgnore
	@Column(name="updated_at")
	@JsonFormat(pattern="dd/MM/yyy HH:mm:ss")
	private Date updatedAt = new Date();

	
	//bi-directional many-to-many association to Bill
	@JsonIgnore
	@ManyToMany(mappedBy="discounts")
	private Set<Bill> bills;
}
