package com.travelling_project.controller;
import com.travelling_project.entity.Traveller;
import com.travelling_project.repository.TravellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TravellerController {

    @Autowired
    private TravellerRepository travellerRepository;

    @GetMapping("/travellers/form")
    public String showForm(Model model) {
        model.addAttribute("traveller", new Traveller()); // Create an empty Traveller object
        List<Traveller> travellers = travellerRepository.findAll();
        model.addAttribute("travellers", travellers); // Add list of travellers to the model
        return "traveller-form"; // Return Thymeleaf template name
    }

    @PostMapping("/travellers/api/travellers")
    public String createTraveller(@ModelAttribute Traveller traveller, RedirectAttributes redirectAttributes) {
        travellerRepository.save(traveller); // Save the traveller
        redirectAttributes.addFlashAttribute("successMessage", "Traveller added successfully!");
        return "redirect:/travellers/form"; // Redirect to the form after submission
    }

    @GetMapping
    public String getAllTravellers(Model model) {
        List<Traveller> travellers = travellerRepository.findAll();
        model.addAttribute("travellers", travellers);
        return "traveller-form"; // Display the travellers in the form
    }
}
