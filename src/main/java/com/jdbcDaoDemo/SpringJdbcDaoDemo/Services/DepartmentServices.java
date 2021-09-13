package com.jdbcDaoDemo.SpringJdbcDaoDemo.Services;
import com.jdbcDaoDemo.SpringJdbcDaoDemo.Model.Department;
import java.util.List;

public interface DepartmentServices {
     List<Department> getDepartmentData();
    Department getDepartmentDataByID(int deptId);
    String updateDepartment(Department deptartment);
    String addNewDepartment(Department Department);
    String deleteRecord (int deptId);
    Integer insertDepartment (Department department);

}
