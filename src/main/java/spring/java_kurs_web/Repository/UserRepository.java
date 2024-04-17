package spring.java_kurs_web.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.java_kurs_web.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
