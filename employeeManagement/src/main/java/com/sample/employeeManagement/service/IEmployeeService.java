package com.sample.employeeManagement.service;


import com.sample.employeeManagement.dto.EmployeeDto;


import java.util.List;

public interface IEmployeeService {
    EmployeeDto create(EmployeeDto e);

    void removeAllEmployees();

    List<EmployeeDto> findDistinctByCriteria(String criteria);

}
