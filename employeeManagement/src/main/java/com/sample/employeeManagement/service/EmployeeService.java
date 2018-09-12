package com.sample.employeeManagement.service;

import com.sample.employeeManagement.dao.EmployeeRepository;
import com.sample.employeeManagement.dao.IEmployeeDao;
import com.sample.employeeManagement.dto.EmployeeDto;
import com.sample.employeeManagement.model.Employee;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    IEmployeeDao employeeDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public EmployeeDto create(EmployeeDto e) {
        DozerBeanMapper mapper = new DozerBeanMapper();
        Employee employee = employeeRepository.save(mapper.map(e, Employee.class));
        return mapper.map(employee, EmployeeDto.class);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<EmployeeDto> findDistinctByCriteria(String criteria) {
        List<EmployeeDto> employeeDtoList = null;
        DozerBeanMapper mapper = new DozerBeanMapper();
        List<Employee> employees = employeeDao.findDistinctByCriteria(criteria);
        for (int i = 0; i < employees.size(); i++) {
            employeeDtoList.add(mapper.map(employees.get(i), EmployeeDto.class));
        }
        return employeeDtoList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeAllEmployees() {
        employeeRepository.deleteAll();
    }


}
