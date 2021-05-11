package be.ucll.student.taskmanager.service;

import java.util.List;

import be.ucll.student.taskmanager.DTO.SubTaskDTO;
import be.ucll.student.taskmanager.DTO.TaskDTO;

public interface TaskService {
    public List<TaskDTO> getTasks();

    public TaskDTO getTask(Long taskId);

    public void addTask(TaskDTO taskDTO);

    public void editTask(TaskDTO taskDTO);

    public void deleteTask(Long taskId);

    public void addSubTask(Long taskId, SubTaskDTO subTaskDTO);

    public void editSubTask(Long taskId, SubTaskDTO subTaskDTO);

    public void deleteSubTask(Long taskId, Long subTaskId);

    public List<SubTaskDTO> getSubTasks(Long taskId);

    public SubTaskDTO getSubTask(Long taskId, Long subTaskId);
}