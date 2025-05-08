package com.drivinglearners.driving_learners.controller;

import com.drivinglearners.driving_learners.model.Learner;
import com.drivinglearners.driving_learners.service.LearnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@Controller
@RequestMapping("/learner") // Prefix all mappings with /learner
public class LearnerController {
    private final LearnerService learnerService;

    public LearnerController(LearnerService learnerService) {
        this.learnerService = learnerService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // Set a default expiry date for new learners
        model.addAttribute("learner", new Learner("", "", "", "", "", LocalDate.now().plusYears(1)));
        return "register";
    }

    @PostMapping("/register")
    public String registerLearner(@ModelAttribute Learner learner) throws IOException {
        learnerService.createLearner(learner);
        return "redirect:/learner/view"; // Align with navigation link
    }

    @GetMapping("/view")
    public String listLearners(Model model) throws IOException {
        model.addAttribute("learners", learnerService.getAllLearners());
        return "learner-list";
    }

    @GetMapping("/profile/{id}")
    public String viewLearnerProfile(@PathVariable String id, Model model) throws IOException {
        Learner learner = learnerService.getLearnerById(id);
        if (learner == null) {
            model.addAttribute("error", "Learner not found");
            return "error";
        }
        model.addAttribute("learner", learner);
        return "learner-profile";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable String id, Model model) throws IOException {
        Learner learner = learnerService.getLearnerById(id);
        if (learner == null) {
            model.addAttribute("error", "Learner not found");
            return "error";
        }
        model.addAttribute("learner", learner);
        return "update-learner";
    }

    @PostMapping("/update")
    public String updateLearner(@ModelAttribute Learner learner) throws IOException {
        boolean updated = learnerService.updateLearner(learner);
        if (!updated) {
            return "error";
        }
        return "redirect:/learner/view";
    }
}