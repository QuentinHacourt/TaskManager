package be.ucll.student.taskmanager.DTO;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

public class TaskDTO {
    private Long id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @NotNull
    // Formats output date when this DTO is 
    // passed through JSON
    @JsonFormat(pattern = "dd/MM/yyyy")
    // Allows dd/MM/yyyy date to be passed 
    // into GET request in JSON
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dueDate;
    private List<SubTaskDTO> subtasks;

    public TaskDTO(
        Long id,
        String title,
        String description,
        LocalDate dueDate,
        List<SubTaskDTO> subTasks
    ){
        setId(id);
        setTitle(title);
        setDescription(description);
        setDueDate(dueDate);
        setSubtasks(subtasks);
    }

    public TaskDTO(){}

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * @return the dueDate
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * @param subtasks the subtasks to set
     */
    public void setSubtasks(List<SubTaskDTO> subtasks) {
        this.subtasks = subtasks;
    }

    /**
     * @return the subtasks
     */
    public List<SubTaskDTO> getSubtasks() {
        return subtasks;
    }

}