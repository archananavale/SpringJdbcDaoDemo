package com.jdbcDaoDemo.SpringJdbcDaoDemo.Dao;

import com.jdbcDaoDemo.SpringJdbcDaoDemo.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository   // this class is related to database

public class EmployeeDao {
@Autowired
    private JdbcTemplate jdbcTemplate; //provided be jdbc API

    public List<Employee> getAllEmployee(){
        String sql= "select * from Employee";
        List<Employee> list=null;
        try {
             list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return  list;
    }

    public Employee getEmployeeById(int emplId){
        String sqlQuerry="Select * from Employee where id= ? ";
        Employee empObj=null;
        try {
             empObj = jdbcTemplate.queryForObject(sqlQuerry, new Object[]{emplId}, new int[]{Types.INTEGER},
                    new BeanPropertyRowMapper<>(Employee.class));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return  empObj;

    }

    public  String insertEmployee(Employee employee){
        if(this.getEmployeeById(employee.getId())==null) { //check if data available already
            String strQuerry = "Insert into employee (name, city,dept_id) values (?,?,?)";
           try {
               jdbcTemplate.update(strQuerry, new Object[]{employee.getName(), employee.getCity(), employee.getDepartment().getId()});
           }catch (Exception e )
           {
               System.out.println(e.getMessage());
           }
            return "Employee Record Inserted ";
        }else
            return  this.updateEmployee(employee);

    }
    public  String updateEmployee(Employee employee){
        String strQuerry= "Update Employee Set name=? , city =?  where id = ?";
        try {
            jdbcTemplate.update(strQuerry, new Object[]{employee.getName(), employee.getCity(), employee.getId()});
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return  "Record Updated ";

    }
    public String deleteRecord(int empId){
        String strQurry ="delete from Employee where id=?";
        //jdbcTemplate.update(strQurry,empId);
        try {
            jdbcTemplate.update(strQurry, new Object[]{empId}, new int[]{Types.INTEGER});
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "Record Deleted ";
    }
    public List<Map<String,Object>> getAllData(){
        // check for forein key :
        String strQuerry="select a.id, a.name, a.city, b.name as department_name from employee a, department b where a.dept_id=b.id";
        List<Map<String,Object>> list=null;
        try {
            list = jdbcTemplate.queryForList(strQuerry);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }


}
