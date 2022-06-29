package net.codejava.repository;
 
import net.codejava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    boolean existsByEmail(String email);
    /*@Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);*/


     
}