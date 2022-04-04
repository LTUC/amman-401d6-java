package com.asac.demo12.Repositries;

import com.asac.demo12.Models.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Integer> {
}
