package be.ucll.student.taskmanager.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import be.ucll.student.taskmanager.DTO.SubTaskDTO;
import be.ucll.student.taskmanager.DTO.TaskDTO;
import be.ucll.student.taskmanager.service.TaskService;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping()
    public String getTasks(Model model) {
        model.addAttribute("tasks", service.getTasks());
        return "tasks";
    }

    @RequestMapping("/{id}")
    public String getTask(Model model, @PathVariable("id") Long id) {
        model.addAttribute("taskDTO", service.getTask(id));    
        return "task";
    }

    @GetMapping("/new")
    public String addTaskPage(Model model) {
        model.addAttribute("taskDTO", new TaskDTO());
        return "addTask";
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute @Valid TaskDTO taskDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addTask";
        }

        service.addTask(taskDTO);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        service.deleteTask(id);

        

        return "/tasks";
    }

    @GetMapping("/edit/{id}")
    public String editTaskPath(Model model, @PathVariable("id") Long taskId) {
        TaskDTO taskDTO = service.getTask(taskId);
        model.addAttribute("taskDTO", taskDTO);
        return "editTask";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") Long id, @Valid TaskDTO taskDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            taskDTO.setId(id);
            return "update-user";
        }

        taskDTO.setId(id);

        service.editTask(taskDTO);
        model.addAttribute("taskDTO", service.getTask(id));
        return "task";
    }

    @RequestMapping("/{id}/sub")
    public String getSubTasks(Model model, @PathVariable("id") Long taskId) {
        model.addAttribute("subTaskDTOs", service.getSubTasks(taskId));
        // TODO: binding results?
        

        return "subTasks";
    }

    @GetMapping("/{id}/sub/create")
    public String createSubTaskForm(Model model, @PathVariable("id") Long taskId) {
        model.addAttribute("id", taskId);
        model.addAttribute("subTaskDTO", new SubTaskDTO());
        return "addSubTask";
    }

    @PostMapping("/{id}/sub/create")
    public String createSubTask(Model model, @PathVariable("id") Long taskId,
            @ModelAttribute SubTaskDTO subTaskDTO) {

        // TODO: binding results?
        service.addSubTask(taskId, subTaskDTO);

        

        return "redirect:/tasks/" + Long.toString(taskId);
    }

    

}