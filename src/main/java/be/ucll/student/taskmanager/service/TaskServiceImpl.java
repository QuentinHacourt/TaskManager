package be.ucll.student.taskmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ucll.student.taskmanager.DTO.SubTaskDTO;
import be.ucll.student.taskmanager.DTO.TaskDTO;
import be.ucll.student.taskmanager.model.SubTask;
import be.ucll.student.taskmanager.model.Task;
import be.ucll.student.taskmanager.repository.TaskRepositoryJPA;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepositoryJPA repository;

    @Autowired
    TaskServiceImpl(TaskRepositoryJPA repository) {
        this.repository = repository;
        
    }

    

    @Override
    public List<TaskDTO> getTasks() {
        return repository.findAll().stream().map(h -> {
            TaskDTO dto = new TaskDTO();
            List<SubTaskDTO> subTaskDTOs = new ArrayList<>();

            dto.setId(h.getId());
            dto.setTitle(h.getTitle());
            dto.setDescription(h.getDescription());
            dto.setDueDate(h.getDueDate());
            for (SubTask t : h.getSubTasks()) {
                subTaskDTOs.add(new SubTaskDTO(
                    t.getId(), 
                    t.getTitle(), 
                    t.getDescription()
                ));
            }
			
			return dto;
		}).collect(Collectors.toList());
    }

    @Override
    public void addTask(final TaskDTO taskDTO) {
        Task task = new Task(
            taskDTO.getTitle(), 
            taskDTO.getDueDate(), 
            taskDTO.getDescription()
        );
        
        repository.save(task);
    }

    @Override
    public void editTask(final TaskDTO taskDTO) {
        
        for (Task task: repository.findAll()) {
            if(task.getId() == taskDTO.getId()){
                task.setTitle(taskDTO.getTitle());
                task.setDescription(taskDTO.getDescription());
                task.setDueDate(taskDTO.getDueDate());
                repository.save(task);
            }
        }

    }

    @Override
    public void deleteTask(Long taskId) {
        repository.deleteById(taskId);
    }

    @Override
    public void addSubTask(Long taskId, SubTaskDTO subTaskDTO) {
        SubTask subTask;
        
        for (Task task : repository.findAll()) {
            if (task.getId() == taskId) {
                subTask = new SubTask(
                    subTaskDTO.getTitle(), 
                    subTaskDTO.getDescription(), 
                    task
                );
                task.addSubTask(subTask);
                repository.save(task);
            }
        }

    }

    @Override
    public TaskDTO getTask(Long taskId) {
        TaskDTO taskDTO;
        Task task;
        ArrayList<SubTaskDTO> subTasks = new ArrayList<>();

        task = getTaskById(taskId);

        for (SubTask subTask : task.getSubTasks()) {
            subTasks.add(new SubTaskDTO(subTask.getId(), subTask.getTitle(), subTask.getDescription()));
        }
        
        taskDTO = new TaskDTO(task.getId(), task.getTitle(), task.getDescription(), task.getDueDate(), subTasks);

        return taskDTO;
    }

    @Override
    public void editSubTask(Long taskId, SubTaskDTO subTaskDTO) {
        Task task = getTaskById(taskId);

        task.editSubTask(
            subTaskDTO.getId(), 
            subTaskDTO.getTitle(), 
            subTaskDTO.getDescription());

        repository.save(task);


        
    }

    @Override
    public void deleteSubTask(Long taskId, Long subTaskId) {
        Task task;

        
        task = getTaskById(taskId);

        task.deleteSubTask(subTaskId);

        repository.save(task);
    }

    @Override
    public List<SubTaskDTO> getSubTasks(Long taskId) {
        Task task;
        ArrayList<SubTaskDTO> subTaskDTOs = new ArrayList<>();


        task = getTaskById(taskId);

        for (SubTask subTask : task.getSubTasks()) {
            subTaskDTOs.add(
                new SubTaskDTO(
                    subTask.getId(), 
                    subTask.getTitle(),
                    subTask.getDescription()
                )
            );
        }

        return subTaskDTOs;
    }

    

    @Override
    public SubTaskDTO getSubTask(Long taskId, Long subTaskId) {
        Task task;
        SubTask subTask;
        SubTaskDTO subTaskDTO;

        task = getTaskById(taskId);
        subTask = task.getSubtask(subTaskId);

        subTaskDTO = new SubTaskDTO(
            subTask.getId(), 
            subTask.getTitle(), 
            subTask.getDescription()
        );

        return subTaskDTO;
    }

    private Task getTaskById(Long id){
        return repository.getOne(id);
    }
    
    
}