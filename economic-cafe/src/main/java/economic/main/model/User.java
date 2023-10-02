package economic.main.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "[User]")
public class User implements UserDetails{

    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    private String username;

	private String email = null;

	@Column(name="first_name")
	private String firstName = null;

    @Column(name="last_name")
	private String lastName = null;

	@JsonIgnore
    private String password;

	private boolean genther = false;

	private String phone = null;

    private String avatar = null;

	private String background = null;

	@Transient
	private String roleName;

	@JsonFormat(pattern="dd/MM/yyy HH:mm:ss")
	private Date birthday = null;

	@Column(name="ship_address")
	private String shipAddress = null;

	@JsonIgnore
	@Column(name="refresh_token")
	private String refreshToken = null;

	@JsonIgnore
	@Column(name="logout")
	private Date logout = null;

	@JsonIgnore
    private boolean deleted =false;

	@Column(name="updated_at")
	@JsonFormat(pattern="dd/MM/yyy HH:mm:ss")
	private Date updatedAt = new Date();

    @Column(name="created_at")
	@JsonFormat(pattern="dd/MM/yyy HH:mm:ss")
	private Date createdAt = new Date();

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private Set<Bill> bills;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private Set<Comment> comments;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private Set<LikesComments> likesComments;

	// return name role for json
	public String getRoleName(){
		return this.role.getName();
	}

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="[role]", nullable = false, referencedColumnName = "[name]")
	private Role role = Role.builder().name("ROLE_USER").build();


	
	
	@JsonIgnore
	@Override
	public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(this.role.getName()));
        return authorities;
    }

	
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() { 
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

	
}
