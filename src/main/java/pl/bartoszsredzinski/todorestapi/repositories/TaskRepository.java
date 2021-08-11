package pl.bartoszsredzinski.todorestapi.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bartoszsredzinski.todorestapi.models.Task;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByDate(Date date);
    List<Task> findByStatus(String status);
}
