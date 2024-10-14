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

    @GetMapping("/{id}")
    public ResponseEntity<Traveller> getTravellerById(@PathVariable Long id) {
        return travellerRepository.findById(id)
                .map(traveller -> ResponseEntity.ok().body(traveller))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Traveller> updateTraveller(@PathVariable Long id, @RequestBody Traveller travellerDetails) {
        return travellerRepository.findById(id)
                .map(traveller -> {
                    traveller.setName(travellerDetails.getName());
                    traveller.setDestination(travellerDetails.getDestination());
                    traveller.setEmail(travellerDetails.getEmail());
                    Traveller updatedTraveller = travellerRepository.save(traveller);
                    return ResponseEntity.ok(updatedTraveller);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTraveller(@PathVariable Long id) {
        return travellerRepository.findById(id)
                .map(traveller -> {
                    travellerRepository.delete(traveller);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
