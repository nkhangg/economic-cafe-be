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
public class Receiver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String address;

	private String email;

	private String name;

	private String phone;

    private boolean deleted = false;

    @Column(name="created_at")
	private Date createdAt = new Date();

	@Column(name="updated_at")
	private Date updatedAt = new Date();

	//bi-directional many-to-one association to Bill
	@OneToMany(mappedBy="receiver")
	private Set<Bill> bills;
}
