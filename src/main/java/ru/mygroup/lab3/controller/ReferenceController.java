package ru.mygroup.lab3.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mygroup.lab3.StudentGroup;
import ru.mygroup.lab3.University;
import ru.mygroup.lab3.repository.StudentGroupRepository;
import ru.mygroup.lab3.repository.UniversityRepository;

@Controller
@RequestMapping("/references")
public class ReferenceController {
    private final UniversityRepository universityRepository;
    private final StudentGroupRepository studentGroupRepository;

    public ReferenceController(UniversityRepository universityRepository,
                               StudentGroupRepository studentGroupRepository) {
        this.universityRepository = universityRepository;
        this.studentGroupRepository = studentGroupRepository;
    }

    @GetMapping
    public String references(Model model) {
        model.addAttribute("universities", universityRepository.findAll());
        model.addAttribute("groups", studentGroupRepository.findAll());
        model.addAttribute("university", new University());
        model.addAttribute("group", new StudentGroup());

        return "references";
    }

    @PostMapping("/university")
    public String addUniversity(
            @ModelAttribute("university") @Valid University university,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("universities", universityRepository.findAll());
            model.addAttribute("groups", studentGroupRepository.findAll());
            model.addAttribute("group", new StudentGroup());

            return "references";
        }

        universityRepository.save(university);

        return "redirect:/references";
    }

    @PostMapping("/group")
    public String addGroup(
            @ModelAttribute("group") @Valid StudentGroup group,
            BindingResult result,
            @RequestParam Long universityId,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("universities", universityRepository.findAll());
            model.addAttribute("groups", studentGroupRepository.findAll());
            model.addAttribute("university", new University());

            return "references";
        }

        var university = universityRepository.findById(universityId);

        if (university.isEmpty()) {
            model.addAttribute("universities", universityRepository.findAll());
            model.addAttribute("groups", studentGroupRepository.findAll());
            model.addAttribute("university", new University());

            return "references";
        }

        group.setUniversity(university.get());
        studentGroupRepository.save(group);

        return "redirect:/references";
    }
}
