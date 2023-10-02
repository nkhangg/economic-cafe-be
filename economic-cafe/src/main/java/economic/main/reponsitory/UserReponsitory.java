package economic.main.reponsitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import economic.main.model.User;


public interface UserReponsitory extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    @Modifying(clearAutomatically = true)
    @Query("update User u set u.refreshToken = :refreshToken where u.id = :userId")
    int setRefreshToken(@Param("refreshToken") String refreshToken, @Param("userId") Integer userId);

    @Query("SELECT u.refreshToken FROM User u where u.id = :id") 
    String findResfreshToken(@Param("id") Integer idUser);

    @Query("select u from User u where u.deleted = false and u.logout = null and u.username = :username")
    Optional<User> findByUsernameAndCheckLogin(@Param("username") String username);
}
