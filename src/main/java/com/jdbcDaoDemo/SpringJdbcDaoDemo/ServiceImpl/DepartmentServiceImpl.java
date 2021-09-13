package com.jdbcDaoDemo.SpringJdbcDaoDemo.ServiceImpl;

import com.jdbcDaoDemo.SpringJdbcDaoDemo.Dao.DepartmentDao;
import com.jdbcDaoDemo.SpringJdbcDaoDemo.Model.Department;
import com.jdbcDaoDemo.SpringJdbcDaoDemo.Model.Employee;
import com.jdbcDaoDemo.SpringJdbcDaoDemo.Services.DepartmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentServices {
    @Autowired
    private DepartmentDao departmentDao;
    @Override
    public List<Department> getDepartmentData() {
        return  departmentDao.getAllDepartmentData();
    }

    @Override
    public Department getDepartmentDataByID(int deptId) {
        return departmentDao.getDepartmentDataByID(deptId);
    }

    @Override
    public String updateDepartment(Department deptartment) {
        return departmentDao.updateDepartment(deptartment);
    }

    @Override
    public String addNewDepartment(Department department) {
        return departmentDao.addNewDepartment(department);
    }

    @Override
    public String deleteRecord(int deptId) {
        return departmentDao.deleteRecord(deptId);
    }

    @Override
    public Integer insertDepartment(Department department) {
        return departmentDao.insertDepartment(department);
    }


}
