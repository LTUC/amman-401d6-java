package com.asac.demo16Security.Repositries;

import com.asac.demo16Security.Models.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Integer> {
}
