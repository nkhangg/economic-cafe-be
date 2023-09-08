package economic.main.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import economic.main.model.Role;

public interface RoleReponsitory extends JpaRepository<Role, String>{
    
}
