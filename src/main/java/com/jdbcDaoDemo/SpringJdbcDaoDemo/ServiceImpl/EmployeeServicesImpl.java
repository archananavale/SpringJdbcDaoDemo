package com.jdbcDaoDemo.SpringJdbcDaoDemo.ServiceImpl;

import com.jdbcDaoDemo.SpringJdbcDaoDemo.Dao.EmployeeDao;
import com.jdbcDaoDemo.SpringJdbcDaoDemo.Model.Employee;
import com.jdbcDaoDemo.SpringJdbcDaoDemo.Services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeServicesImpl implements EmployeeServices {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public List<Employee> getData() {
        return employeeDao.getAllEmployee();
    }

    @Override
    public Employee getDataByID(int emplID) {
        return employeeDao.getEmployeeById(emplID);
    }

    @Override
    public String updateEmployee(Employee employee) {
        return employeeDao.updateEmployee(employee);
    }

    @Override
    public String insertEmployee(Employee employee) {
        return employeeDao.insertEmployee(employee);
    }

    @Override
    public String deleteRecord(int empId) {
        return employeeDao.deleteRecord(empId) ;
    }

    @Override
    public List<Map<String, Object>> getAllData() {
        return employeeDao.getAllData();
    }

}
