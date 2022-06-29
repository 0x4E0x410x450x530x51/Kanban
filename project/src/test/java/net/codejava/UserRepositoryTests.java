package net.codejava;
 
import static org.assertj.core.api.Assertions.assertThat;

import net.codejava.model.User;
import net.codejava.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
 
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
 
    @Autowired
    private TestEntityManager entityManager;
     
    @Autowired
    private UserRepository repo;
    
    @AutoConfigureTestDatabase(replace = Replace.NONE)
    @Rollback(false)
    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("noah@ubs.com");
        user.setPassword("1234");
        user.setFirstName("Noah");
        user.setLastName("B");
         
        User savedUser = repo.save(user);
         
        User existUser = entityManager.find(User.class, savedUser.getId());
         
        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
         
    }

    // test methods go below
}