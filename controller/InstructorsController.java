package com.drivinglearners.driving_learners.controller;

import com.drivinglearners.driving_learners.service.InstructorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class InstructorsController {
    private final InstructorService instructorService;

    public InstructorsController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/instructors")
    public String listInstructors(Model model) throws IOException {
        model.addAttribute("instructors", instructorService.getAllInstructors());
        return "instructor-list";
    }
}