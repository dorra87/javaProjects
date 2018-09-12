package com.sample.employeeManagement.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.employeeManagement.dto.EmployeeDto;
import com.sample.employeeManagement.service.IEmployeeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private IEmployeeService employeeService;

    /**
     * upload json file containing employees details
     *
     * @param fileLoaded
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void uploadEmployees(@RequestParam("fileLoaded") MultipartFile fileLoaded) {
        List<EmployeeDto> employees = null;
        try {
            //InputStream inputStream = TypeReference.class.getResourceAsStream("/json/employees.json");
            ObjectMapper mapper = new ObjectMapper();
            employees = mapper.readValue(fileLoaded.getInputStream(), mapper.getTypeFactory().constructCollectionType(List.class, EmployeeDto.class));
        } catch (Exception e) {
            LOGGER.warn("Unable to read employees file for this reason: {}", e.getMessage());
        }
        if (!employees.isEmpty()) {
            employeeService.removeAllEmployees();
            employees.forEach(employee -> employeeService.create(employee));
            LOGGER.warn("Employees saved successfully");
        }

    }

    /**
     * get distinct employee list depending on field criteria for grouping
     *
     * @param field
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<EmployeeDto> getEmployees(@RequestParam(value = "field", required = true) String field) {
        return employeeService.findDistinctByCriteria(field);

    }
}