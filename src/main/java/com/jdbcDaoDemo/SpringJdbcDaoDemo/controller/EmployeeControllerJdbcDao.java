package com.jdbcDaoDemo.SpringJdbcDaoDemo.controller;

import com.jdbcDaoDemo.SpringJdbcDaoDemo.Dao.DepartmentDao;
import com.jdbcDaoDemo.SpringJdbcDaoDemo.Model.Department;
import com.jdbcDaoDemo.SpringJdbcDaoDemo.Model.Employee;
import com.jdbcDaoDemo.SpringJdbcDaoDemo.ServiceImpl.DepartmentServiceImpl;
import com.jdbcDaoDemo.SpringJdbcDaoDemo.ServiceImpl.EmployeeServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class EmployeeControllerJdbcDao {

        @Autowired
        private EmployeeServicesImpl employeeServicesImpl;
        @Autowired
        private DepartmentServiceImpl departmentServiceImpl;
        @GetMapping("/getEmployee")
        public List<Employee> getData(){
            return  employeeServicesImpl.getData();
        }

        @GetMapping("/getEmployeeById/{empId}")
        public Employee getEmployeeById(@PathVariable int empId){
            return employeeServicesImpl.getDataByID(empId);
        }

        @PostMapping ("/saveEmployee")
        public  String insertEmployee(@RequestBody Employee employee){
            return employeeServicesImpl.insertEmployee(employee);

        }
        @GetMapping("/getAllDetails")
        public List<Map<String,Object>> getAllData(){
            return employeeServicesImpl.getAllData();
        }

        @PutMapping("/updateEmployee")
        public  String updateEmployeeById(@RequestBody Employee employee){

            return employeeServicesImpl.updateEmployee(employee);
        }
        @DeleteMapping("/deleteRecord/{empId}")
        public String deleteRecord (@PathVariable int empId){
            return employeeServicesImpl.deleteRecord(empId);
        }


        @PostMapping("/saveEmployeeAndDepartment")
    public String insertDepartmentAndEmployee(@RequestBody Employee employee){
            if(employee.getDepartment().getId()==null) {
                employee.getDepartment().setId(departmentServiceImpl.insertDepartment(employee.getDepartment()));
            }else{
                Department deptAvailable= departmentServiceImpl.getDepartmentDataByID(employee.getDepartment().getId());
                if(deptAvailable == null )
                    employee.getDepartment().setId(departmentServiceImpl.insertDepartment(employee.getDepartment()));
                else  //update dept for exiting ID
                    departmentServiceImpl.updateDepartment(employee.getDepartment());
            }
            employeeServicesImpl.insertEmployee(employee);
 return "Data Inserted";
        }
    }

/*
Abhijeet Chougule4:00 PM
a) Check if the department id is null
b) If department id in department object is null or not available then check for department exxistence
c) If department is null in step (b) then add department
Abhijeet Chougule4:02 PM
d) After insertion into department get the department id
e) Add the dept_id to the employee object or insert query
 */
