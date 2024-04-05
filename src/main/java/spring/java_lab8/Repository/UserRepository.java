package spring.java_lab8.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.java_lab8.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
