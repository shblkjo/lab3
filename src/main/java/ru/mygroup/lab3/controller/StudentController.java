package ru.mygroup.lab3.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mygroup.lab3.Student;
import ru.mygroup.lab3.repository.StudentGroupRepository;
import ru.mygroup.lab3.repository.StudentRepository;
import ru.mygroup.lab3.repository.UniversityRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentRepository studentRepository;
    private final UniversityRepository universityRepository;
    private final StudentGroupRepository studentGroupRepository;

    public StudentController(
            StudentRepository studentRepository,
            UniversityRepository universityRepository,
            StudentGroupRepository studentGroupRepository) {
        this.studentRepository = studentRepository;
        this.universityRepository = universityRepository;
        this.studentGroupRepository = studentGroupRepository;
    }

    private void addReferences(Model model) {
        model.addAttribute("universities", universityRepository.findAll());
        model.addAttribute("groups", studentGroupRepository.findAll());
    }

    @GetMapping({"", "/main"})
    public String main(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("searchType", "lastName");
        model.addAttribute("searchQuery", "");
        return "main";
    }

    @GetMapping("/new")
    public String newStudent(Model model) {
        model.addAttribute("student", new Student());
        addReferences(model);
        return "new_student";
    }

    @PostMapping("/new")
    public String addStudent(@ModelAttribute("student") @Valid Student student, BindingResult result, Model model) {

        if (result.hasErrors()) {
            addReferences(model);
            return "new_student";
        }

        if (student.getUniversity() == null
                || student.getUniversity().getId() == null
                || student.getStudentGroup() == null
                || student.getStudentGroup().getId() == null) {
            addReferences(model);
            return "new_student";
        }

        var university = universityRepository.findById(
                student.getUniversity().getId());

        var group = studentGroupRepository.findById(
                student.getStudentGroup().getId());

        if (university.isEmpty() || group.isEmpty()) {
            addReferences(model);
            return "new_student";
        }

        student.setUniversity(university.get());
        student.setStudentGroup(group.get());

        studentRepository.save(student);

        return "redirect:/students/main";
    }



    @PostMapping("/find")
    public String findStudents(@RequestParam String searchType,
                               @RequestParam String searchQuery,
                               Model model) {

        if (searchQuery == null || searchQuery.isEmpty()) {
            return "redirect:/students/main";
        }

        List<Student> students = new ArrayList<>();

        if (searchType.equals("lastName")) {
            students = studentRepository
                    .findByLastNameContainingIgnoreCase(searchQuery);
        } else if (searchType.equals("university")) {
            students = studentRepository
                    .findByUniversityNameContainingIgnoreCase(searchQuery);
        } else if (searchType.equals("group")) {
            students = studentRepository
                    .findByStudentGroupNameContainingIgnoreCase(searchQuery);
        } else if (searchType.equals("specialty")) {
            students = studentRepository
                    .findBySpecialtyContainingIgnoreCase(searchQuery);
        } else if (searchType.equals("course")) {
            students = studentRepository.findByCourse(
                    Integer.parseInt(searchQuery));
        }

        model.addAttribute("students", students);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchQuery", searchQuery);

        return "main";
    }


}
