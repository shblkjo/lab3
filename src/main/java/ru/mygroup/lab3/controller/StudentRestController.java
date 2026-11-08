package ru.mygroup.lab3.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.mygroup.lab3.Student;
import ru.mygroup.lab3.StudentDTO;
import ru.mygroup.lab3.StudentGroup;
import ru.mygroup.lab3.University;
import ru.mygroup.lab3.repository.StudentGroupRepository;
import ru.mygroup.lab3.repository.StudentRepository;
import ru.mygroup.lab3.repository.UniversityRepository;

import java.time.LocalDate;
import java.util.Map;

@RequestMapping("/api/students")
@RestController
public class StudentRestController {
    private final StudentRepository studentRepository;
    private final UniversityRepository universityRepository;
    private final StudentGroupRepository studentGroupRepository;

    public StudentRestController(StudentRepository studentRepository, UniversityRepository universityRepository, StudentGroupRepository studentGroupRepository) {
        this.studentRepository = studentRepository;
        this.universityRepository = universityRepository;
        this.studentGroupRepository = studentGroupRepository;
    }

    @GetMapping
    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Студент с ID " + id + " не найден"
                        ));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody StudentDTO studentDTO) {
        University university = universityRepository
                .findById(studentDTO.getUniversityId())
                .orElseThrow(() ->
                        new EntityNotFoundException("ВУЗ не найден"));

        StudentGroup studentGroup = studentGroupRepository
                .findById(studentDTO.getStudentGroupId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Группа не найдена"));

        Student student = new Student();

        student.setLastName(studentDTO.getLastName());
        student.setFirstName(studentDTO.getFirstName());
        student.setPatronymic(studentDTO.getPatronymic());
        student.setGender(studentDTO.getGender());
        student.setNationality(studentDTO.getNationality());
        student.setHeight(studentDTO.getHeight());
        student.setWeight(studentDTO.getWeight());
        student.setBirthDate(studentDTO.getBirthDate());
        student.setPhone(studentDTO.getPhone());
        student.setPostalCode(studentDTO.getPostalCode());
        student.setCountry(studentDTO.getCountry());
        student.setRegion(studentDTO.getRegion());
        student.setDistrict(studentDTO.getDistrict());
        student.setCity(studentDTO.getCity());
        student.setStreet(studentDTO.getStreet());
        student.setHouse(studentDTO.getHouse());
        student.setApartment(studentDTO.getApartment());
        student.setCourse(studentDTO.getCourse());
        student.setAverageGrade(studentDTO.getAverageGrade());
        student.setSpecialty(studentDTO.getSpecialty());
        student.setUniversity(university);
        student.setStudentGroup(studentGroup);

        return studentRepository.save(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable("id") Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Студент с ID " + id + " не найден"));
        University university = universityRepository
                .findById(studentDTO.getUniversityId())
                .orElseThrow(() ->
                        new EntityNotFoundException("ВУЗ не найден"));
        StudentGroup studentGroup = studentGroupRepository
                .findById(studentDTO.getStudentGroupId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Группа не найдена"));

        student.setLastName(studentDTO.getLastName());
        student.setFirstName(studentDTO.getFirstName());
        student.setPatronymic(studentDTO.getPatronymic());
        student.setGender(studentDTO.getGender());
        student.setNationality(studentDTO.getNationality());
        student.setHeight(studentDTO.getHeight());
        student.setWeight(studentDTO.getWeight());
        student.setBirthDate(studentDTO.getBirthDate());
        student.setPhone(studentDTO.getPhone());
        student.setPostalCode(studentDTO.getPostalCode());
        student.setCountry(studentDTO.getCountry());
        student.setRegion(studentDTO.getRegion());
        student.setDistrict(studentDTO.getDistrict());
        student.setCity(studentDTO.getCity());
        student.setStreet(studentDTO.getStreet());
        student.setHouse(studentDTO.getHouse());
        student.setApartment(studentDTO.getApartment());
        student.setCourse(studentDTO.getCourse());
        student.setAverageGrade(studentDTO.getAverageGrade());
        student.setSpecialty(studentDTO.getSpecialty());
        student.setUniversity(university);
        student.setStudentGroup(studentGroup);

        return studentRepository.save(student);
    }

    // Частичное изменение студента
    @PatchMapping("/{id}")
    public Student partialUpdateStudent(
            @PathVariable("id") Long id,
            @RequestBody Map<String, Object> updates) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Студент с ID " + id + " не найден"));

        if (updates.containsKey("lastName")) {
            student.setLastName((String) updates.get("lastName"));
        }
        if (updates.containsKey("firstName")) {
            student.setFirstName((String) updates.get("firstName"));
        }
        if (updates.containsKey("patronymic")) {
            student.setPatronymic((String) updates.get("patronymic"));
        }
        if (updates.containsKey("gender")) {
            student.setGender((String) updates.get("gender"));
        }
        if (updates.containsKey("nationality")) {
            student.setNationality((String) updates.get("nationality"));
        }
        if (updates.containsKey("height")) {
            Double height = ((Number) updates.get("height")).doubleValue();
            if (height < 50 || height > 250) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Рост должен быть от 50 до 250 см");
            }
            student.setHeight(height);
        }
        if (updates.containsKey("weight")) {
            Double weight = ((Number) updates.get("weight")).doubleValue();
            if (weight < 2 || weight > 350) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Вес должен быть от 2 до 350 кг");
            }

            student.setWeight(weight);
        }
        if (updates.containsKey("birthDate")) {
            LocalDate birthDate = LocalDate.parse((String) updates.get("birthDate"));
            if (!birthDate.isBefore(LocalDate.now())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Дата рождения должна быть в прошлом");
            }
            student.setBirthDate(birthDate);
        }
        if (updates.containsKey("phone")) {
            String phone = (String) updates.get("phone");
            if (phone.length() < 5 || phone.length() > 20) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Проверьте номер телефона");
            }
            student.setPhone(phone);
        }
        if (updates.containsKey("postalCode")) {
            student.setPostalCode((String) updates.get("postalCode"));
        }
        if (updates.containsKey("country")) {
            student.setCountry((String) updates.get("country"));
        }
        if (updates.containsKey("region")) {
            student.setRegion((String) updates.get("region"));
        }
        if (updates.containsKey("district")) {
            student.setDistrict((String) updates.get("district"));
        }
        if (updates.containsKey("city")) {
            student.setCity((String) updates.get("city"));
        }
        if (updates.containsKey("street")) {
            student.setStreet((String) updates.get("street"));
        }
        if (updates.containsKey("house")) {
            student.setHouse((String) updates.get("house"));
        }
        if (updates.containsKey("apartment")) {
            student.setApartment((String) updates.get("apartment"));
        }
        if (updates.containsKey("course")) {
            Integer course = ((Number) updates.get("course")).intValue();
            if (course < 1 || course > 6) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Курс должен быть от 1 до 6");
            }
            student.setCourse(course);
        }
        if (updates.containsKey("averageGrade")) {
            Double averageGrade = ((Number) updates.get("averageGrade")).doubleValue();
            if (averageGrade < 0 || averageGrade > 10) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Средний балл должен быть от 0 до 10");
            }
            student.setAverageGrade(averageGrade);
        }
        if (updates.containsKey("specialty")) {
            student.setSpecialty((String) updates.get("specialty"));
        }

        if (updates.containsKey("universityId")) {
            Long universityId = ((Number) updates.get("universityId")).longValue();
            University university = universityRepository.findById(universityId)
                    .orElseThrow(() -> new EntityNotFoundException("ВУЗ не найден"));
            student.setUniversity(university);
        }

        if (updates.containsKey("studentGroupId")) {
            Long groupId = ((Number) updates.get("studentGroupId")).longValue();

            StudentGroup studentGroup = studentGroupRepository.findById(groupId)
                    .orElseThrow(() -> new EntityNotFoundException("Группа не найдена"));

            student.setStudentGroup(studentGroup);
        }

        return studentRepository.save(student);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable("id") Long id) {
        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Студент с ID " + id + " не найден");
        }

        studentRepository.deleteById(id);
    }
}
