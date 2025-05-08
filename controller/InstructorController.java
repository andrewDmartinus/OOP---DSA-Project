package com.drivinglearners.driving_learners.controller;

import com.drivinglearners.driving_learners.model.Instructor;
import com.drivinglearners.driving_learners.service.InstructorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/instructor")
public class InstructorController {
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("instructor", new Instructor("", "", "", "", 0));
        return "instructor-register";
    }

    @PostMapping("/register")
    public String registerInstructor(@ModelAttribute Instructor instructor, Model model) {
        try {
            instructorService.createInstructor(instructor);
            return "redirect:/instructors";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "instructor-register";
        } catch (IOException e) {
            model.addAttribute("error", "Failed to save instructor: " + e.getMessage());
            return "instructor-register";
        }
    }
}