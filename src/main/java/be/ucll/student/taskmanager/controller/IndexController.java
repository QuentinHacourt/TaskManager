package be.ucll.student.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @Autowired
    public IndexController(){

    }

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }
}