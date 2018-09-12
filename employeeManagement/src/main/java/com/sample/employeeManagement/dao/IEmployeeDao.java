package com.sample.employeeManagement.dao;

import com.sample.employeeManagement.model.Employee;

import java.util.List;

public interface IEmployeeDao {
    List<Employee> findDistinctByCriteria(String criteria);
}
