package com.drivinglearners.driving_learners.model;

import java.time.LocalDate;

public class BeginnerLearner extends Learner {
    public BeginnerLearner(String id, String name, String email, String contact, LocalDate expiryDate) {
        super(id, name, email, "Beginner", contact, expiryDate);
    }

    @Override
    public String trackProgress() {
        return "Beginner progress for " + getName() + ": Basic skills in progress.";
    }
}