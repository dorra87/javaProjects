package com.sample.employeeManagement.dao;

import com.sample.employeeManagement.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.stereotype.Repository;

import java.util.List;


import org.springframework.data.mongodb.core.aggregation.Aggregation;


@Repository
public class EmployeeDaoImpl implements IEmployeeDao {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List findDistinctByCriteria(String criteria) {
        GroupOperation group = Aggregation.group(criteria);
        Aggregation aggregation = Aggregation.newAggregation(group);
        AggregationResults<Employee> result = mongoTemplate.aggregate(aggregation, Employee.class, Employee.class);
        return result.getMappedResults();
    }


}
