package com.example.jparepository_example.Repository;

import com.example.jparepository_example.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
