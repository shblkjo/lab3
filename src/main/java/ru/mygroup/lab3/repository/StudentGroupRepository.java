package ru.mygroup.lab3.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mygroup.lab3.StudentGroup;

import java.util.List;

public interface StudentGroupRepository
        extends CrudRepository<StudentGroup, Long> {

    List<StudentGroup> findByUniversityId(Long universityId);
}