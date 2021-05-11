package be.ucll.student.taskmanager.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String title;
    @NotNull
    // Formats output date when this DTO is 
    // passed through JSON
    @JsonFormat(pattern = "dd/MM/yyyy")
    // Allows dd/MM/yyyy date to be passed 
    // into GET request in JSON
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dueDate;
    private String description;
    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SubTask> subTasks;

    public Task (String title, LocalDate dueDate, String description){
        setTitle(title);
        setDueDate(dueDate);
        setDescription(description);
        subTasks = new ArrayList<>();
    }

    public Task () {}

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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

    public void addSubTask(SubTask subtask){
        subTasks.add(subtask);
    }

    public void deleteSubTask(Long id){
        for (SubTask subtask : subTasks) {
            if (subtask.getId() == id) {
                subTasks.remove(subtask);
            }
        }
    }

    public void deleteSubTask(SubTask subtask){
        subTasks.remove(subtask);
    }

    public List<SubTask> getSubTasks(){
        return subTasks;
    }

    public SubTask getSubtask(Long id){
        for (SubTask subTask : subTasks) {
            if (subTask.getId() == id) {
                return subTask;
            }
        }
        throw new ModelException("No subtask found for id: " + Long.toString(id));
    }

    public void editSubTask(Long id, String title, String description){
        for (SubTask subtask : subTasks) {
            if (subtask.getId() == id){
                subtask.setTitle(title);
                subtask.setDescription(description);
                return;
            }
        }
        throw new ModelException("No subtask found with id: " + Long.toString(id));
    }

    public boolean equals(Object o){
        if (o instanceof Task) {
            Task newTask = (Task) o;
            if (newTask.getId() == this.id) {
                return true;
            }
        }
        return false;
    }

}