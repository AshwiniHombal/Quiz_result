package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.QuizResult;
import com.example.demo.repository.QuizResultRepository;

@Controller
public class QuizResultController {
    @Autowired
    private QuizResultRepository quizResultRepository;

    // Display a list of all quiz results
    @GetMapping("/results")
    public String showResults(Model model) {
        List<QuizResult> results = quizResultRepository.findAll();
        model.addAttribute("results", results);
        return "results";
    }

    // Display the form for adding a new quiz result
    @GetMapping("/results/add")
    public String showAddForm(Model model) {
        model.addAttribute("result", new QuizResult());
        return "add-result";
    }

    // Process the form submission for adding a new quiz result
    @PostMapping("/results/add")
    public String submitResult(@ModelAttribute QuizResult result) {
        quizResultRepository.save(result);
        return "redirect:/results";
    }

    // Display the form for updating a quiz result by ID
    @GetMapping("/results/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Optional<QuizResult> optionalResult = quizResultRepository.findById(id);
        if (optionalResult.isPresent()) {
            QuizResult result = optionalResult.get();
            model.addAttribute("result", result);
            return "update-result";
        } else {
            return "redirect:/results";
        }
    }

    // Process the form submission for updating a quiz result
    @PostMapping("/results/update/{id}")
    public String updateResult(@PathVariable("id") int id, @ModelAttribute QuizResult updatedResult) {
        Optional<QuizResult> optionalResult = quizResultRepository.findById(id);
        if (optionalResult.isPresent()) {
            QuizResult result = optionalResult.get();
            result.setUsername(updatedResult.getUsername());
            result.setScore(updatedResult.getScore());
            quizResultRepository.save(result);
        }
        return "redirect:/results";
    }

    // Delete a quiz result by ID
    @GetMapping("/results/delete/{id}")
    public String deleteResult(@PathVariable("id") int id) {
        quizResultRepository.deleteById(id);
        return "redirect:/results";
    }
}