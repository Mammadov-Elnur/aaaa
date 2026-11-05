package com.example.bakuguide.controller;

import com.example.bakuguide.model.TourInquiry;
import com.example.bakuguide.repository.LandmarkRepository;
import com.example.bakuguide.repository.TourInquiryRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MainController {

    private static final List<String> TOUR_TYPES = List.of(
            "Old City Walking Tour", "Modern Baku Highlights", "Night Tour & Lights",
            "Absheron Peninsula Day Trip", "Gobustan & Mud Volcanoes", "Custom Tour");

    private final LandmarkRepository landmarks;
    private final TourInquiryRepository inquiries;

    public MainController(LandmarkRepository landmarks, TourInquiryRepository inquiries) {
        this.landmarks = landmarks;
        this.inquiries = inquiries;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("featured", landmarks.findTop4ByOrderByIdAsc());
        return "index";
    }

    @GetMapping("/landmarks")
    public String list(@RequestParam(required = false) String category,
                       @RequestParam(required = false) String q,
                       @RequestParam(defaultValue = "name-asc") String sort,
                       Model model) {

        String cat = (category == null || category.isBlank()) ? null : category.trim();
        String query = (q == null || q.isBlank()) ? null : q.trim();

        Sort order = switch (sort) {
            case "name-desc" -> Sort.by(Sort.Direction.DESC, "name");
            case "category" -> Sort.by("category").and(Sort.by("name"));
            default -> Sort.by(Sort.Direction.ASC, "name");
        };

        model.addAttribute("landmarks", landmarks.search(cat, query, order));
        model.addAttribute("categories", landmarks.findAllCategories());
        model.addAttribute("selectedCategory", cat);
        model.addAttribute("q", query);
        model.addAttribute("sort", sort);
        return "landmarks";
    }

    @GetMapping("/contact")
    public String contactForm(Model model) {
        if (!model.containsAttribute("inquiry")) {
            model.addAttribute("inquiry", new TourInquiry());
        }
        model.addAttribute("tourTypes", TOUR_TYPES);
        return "contact";
    }

    @PostMapping("/contact")
    public String submit(@Valid @ModelAttribute("inquiry") TourInquiry inquiry,
                         BindingResult result,
                         RedirectAttributes redirect,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tourTypes", TOUR_TYPES);
            return "contact";
        }
        inquiries.save(inquiry);
        redirect.addFlashAttribute("success",
                "Thank you, " + inquiry.getName() + "! A local guide will contact you shortly.");
        return "redirect:/contact";
    }
}
