package be.ucll.student.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import be.ucll.student.taskmanager.model.SubTask;

public interface SubTaskRepositoryJPA extends JpaRepository<SubTask, Long> {

}