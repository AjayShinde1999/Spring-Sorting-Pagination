package com.sorting_pagination.controllers;

import com.sorting_pagination.entities.Employee;
import com.sorting_pagination.services.EmployeeService;
import com.sorting_pagination.utils.PageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageResponse> getAllEmployee(@RequestParam(defaultValue = "0", required = false) Integer pageNumber,
                                                       @RequestParam(defaultValue = "2", required = false) Integer pageSize,
                                                       @RequestParam(defaultValue = "id", required = false) String sortBy,
                                                       @RequestParam(defaultValue = "asc",required = false) String order
                                                         ) {
        return ResponseEntity.ok(employeeService.getAllEmployee(pageNumber, pageSize, sortBy, order));
    }


    @GetMapping(path = "/sort")
    public ResponseEntity<List<Employee>> getAllEmployeeSort(@RequestParam(defaultValue = "id", required = false) String sortBy,
                                                             @RequestParam(defaultValue = "asc", required = false) String order) {
        return ResponseEntity.ok(employeeService.getAllEmployeeSort(sortBy, order));
    }

    @GetMapping(path = "/salary/{salary}")
    public ResponseEntity<List<Employee>> getBySalarySort(@PathVariable Long salary,
                                                          @RequestParam(defaultValue = "id", required = false) String sortBy) {
        return ResponseEntity.ok(employeeService.findBySalarySort(salary, sortBy));
    }
}
