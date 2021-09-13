package com.jdbcDaoDemo.SpringJdbcDaoDemo.controller;

import com.jdbcDaoDemo.SpringJdbcDaoDemo.Model.Department;
import com.jdbcDaoDemo.SpringJdbcDaoDemo.Model.Employee;
import com.jdbcDaoDemo.SpringJdbcDaoDemo.ServiceImpl.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class DepartmentControllerJdbcDao {
    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;
    @GetMapping("/getAllDepartmentInfo")
    public List<Department> getAlllDepartment() {
        return departmentServiceImpl.getDepartmentData();
    }

    @GetMapping("/getDepartmentById/{deptId}")
     public Department getDepartmentDataByID(@PathVariable int deptId){
        return departmentServiceImpl.getDepartmentDataByID(deptId);
    }


    @PostMapping("/saveDepartment")
    public  String insertEmployee(@RequestBody Department department){
        return departmentServiceImpl.addNewDepartment(department);

    }


    @PutMapping("/updateDepartment")
    public  String updateEmployeeById(@RequestBody Department department){

        return departmentServiceImpl.updateDepartment(department);
    }
    @DeleteMapping("/deleteRecord/{deptId}")
    public String deleteRecord (@PathVariable int deptID){
        return departmentServiceImpl.deleteRecord(deptID);
    }


}
