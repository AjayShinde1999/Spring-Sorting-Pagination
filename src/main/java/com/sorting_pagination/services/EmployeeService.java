package com.sorting_pagination.services;

import com.sorting_pagination.entities.Employee;
import com.sorting_pagination.repositories.EmployeeRepository;
import com.sorting_pagination.utils.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public PageResponse getAllEmployee(int pageNumber, int pageSize, String sortBy, String order) {
        Sort.Direction direction = "asc".equalsIgnoreCase(order) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Employee> page = employeeRepository.findAll(pageable);
//      return employeeRepository.findAll(pageable).getContent();
        return PageResponse.builder()
                .content(page.getContent())
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .lastPage(page.isLast())
                .build();
    }

    public List<Employee> getAllEmployeeSort(String sortBy, String order) {
        Sort.Direction direction = "asc".equalsIgnoreCase(order) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        return employeeRepository.findAll(sort);
    }

    public List<Employee> findBySalarySort(Long salary, String sortBy) {
        Sort sort = Sort.by(sortBy);
        return employeeRepository.findBySalary(salary, sort);
    }
}
