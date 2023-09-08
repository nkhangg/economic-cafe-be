package economic.main.model;

import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Roles")
public class Role {

   
    
    @Id
    @Column(name = "[name]")
    private String name;

    @Column(name="created_at")
	private Date createdAt = new Date();

	@Column(name="updated_at")
	private Date updatedAt = new Date(); 


    @OneToMany(mappedBy = "role")
    public Set<User> users;
}
