package com.jdbcDaoDemo.SpringJdbcDaoDemo.Services;

import com.jdbcDaoDemo.SpringJdbcDaoDemo.Model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeServices {
     List<Employee> getData();
     Employee getDataByID(int emplID);
     String updateEmployee(Employee employee);
     String insertEmployee(Employee employee);
     String deleteRecord (int empId);
     List<Map<String,Object>> getAllData();
}

