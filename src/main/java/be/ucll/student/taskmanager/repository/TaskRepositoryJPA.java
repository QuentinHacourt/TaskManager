package be.ucll.student.taskmanager.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import be.ucll.student.taskmanager.model.Task;

public interface TaskRepositoryJPA extends JpaRepository<Task, Long> {

}