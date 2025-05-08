package com.drivinglearners.driving_learners.model;

import java.time.LocalDate;

public class AdvancedLearner extends Learner {
    public AdvancedLearner(String id, String name, String email, String contact, LocalDate expiryDate) {
        super(id, name, email, "Advanced", contact, expiryDate);
    }

    @Override
    public String trackProgress() {
        return "Advanced progress for " + getName() + ": Mastering complex skills.";
    }
}