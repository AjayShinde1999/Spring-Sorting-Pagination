package com.sorting_pagination.utils;

import com.sorting_pagination.entities.Employee;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageResponse {

    private List<Employee> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;
}
