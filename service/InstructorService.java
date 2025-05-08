package com.drivinglearners.driving_learners.service;

import com.drivinglearners.driving_learners.model.Instructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class InstructorService {
    private static final String FILE_PATH = "instructors.txt";

    public void createInstructor(Instructor instructor) throws IOException {
        if (instructor == null || instructor.getId() == null || instructor.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("Instructor ID cannot be null or empty.");
        }
        if (instructor.getName() == null || instructor.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Instructor name cannot be null or empty.");
        }
        if (instructor.getEmail() == null || instructor.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Instructor email cannot be null or empty.");
        }
        if (instructor.getContact() == null || instructor.getContact().trim().isEmpty()) {
            throw new IllegalArgumentException("Instructor contact cannot be null or empty.");
        }

        if (getInstructorById(instructor.getId()) != null) {
            throw new IllegalArgumentException("Instructor with ID " + instructor.getId() + " already exists.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(instructor.getId() + "," + instructor.getName() + "," + instructor.getEmail() + "," + instructor.getContact() + "," + instructor.getExperience());
            writer.newLine();
        }
    }

    public List<Instructor> getAllInstructors() throws IOException {
        List<Instructor> instructors = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return instructors;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split(",");
                if (data.length != 5) continue; // Updated to 5 fields (including experience)
                try {
                    Instructor instructor = new Instructor(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]));
                    instructors.add(instructor);
                } catch (Exception e) {
                    System.err.println("Error parsing line in instructors.txt: " + line + " - " + e.getMessage());
                }
            }
        }
        // Bubble sort by experience
        for (int i = 0; i < instructors.size() - 1; i++) {
            for (int j = 0; j < instructors.size() - i - 1; j++) {
                if (instructors.get(j).getExperience() > instructors.get(j + 1).getExperience()) {
                    Instructor temp = instructors.get(j);
                    instructors.set(j, instructors.get(j + 1));
                    instructors.set(j + 1, temp);
                }
            }
        }
        return instructors;
    }

    public Instructor getInstructorById(String id) throws IOException {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return null;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split(",");
                if (data.length != 5) continue; // Updated to 5 fields
                if (data[0].equals(id)) {
                    return new Instructor(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]));
                }
            }
        }
        return null;
    }
}