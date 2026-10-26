package ru.mygroup.lab3.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mygroup.lab3.University;

public interface UniversityRepository
        extends CrudRepository<University, Long> {
}
