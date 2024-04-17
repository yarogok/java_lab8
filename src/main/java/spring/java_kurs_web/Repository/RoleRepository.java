package spring.java_kurs_web.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.java_kurs_web.Model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
