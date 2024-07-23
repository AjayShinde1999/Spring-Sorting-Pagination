package com.sorting_pagination.repositories;

import com.sorting_pagination.entities.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findBySalary(Long salary, Sort sort);
}
