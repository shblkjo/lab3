package ru.mygroup.lab3.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mygroup.lab3.Student;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findByLastNameContainingIgnoreCase(String lastName);
    List<Student> findByUniversityNameContainingIgnoreCase(String name);
    List<Student> findByStudentGroupNameContainingIgnoreCase(String name);
    List<Student> findBySpecialtyContainingIgnoreCase(String specialty);
    List<Student> findByCourse(Integer course);
}