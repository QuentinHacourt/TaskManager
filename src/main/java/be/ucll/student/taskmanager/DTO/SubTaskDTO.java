package be.ucll.student.taskmanager.DTO;

import javax.validation.constraints.NotEmpty;

public class SubTaskDTO {
    private Long id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;

    public SubTaskDTO(){}

    public SubTaskDTO(Long id, String title, String description){
        setId(id);
        setTitle(title);
        setDescription(description);
    }
    
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}