package spring.java_lab8.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.java_lab8.Model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}