package com.jdbcDaoDemo.SpringJdbcDaoDemo.Dao;

import com.jdbcDaoDemo.SpringJdbcDaoDemo.Model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
@Repository
public class DepartmentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Department> getAllDepartmentData(){
        String sqlQuerry= "Select * from Department";
        List<Department> list= jdbcTemplate.query(sqlQuerry,new BeanPropertyRowMapper<>(Department.class));
        return list;
    }
    public Department getDepartmentDataByID(int deptId){
       String strQuerry="select * from department where id=?";
       try {
           Department department = jdbcTemplate.queryForObject(strQuerry, new Object[]{deptId}, new int[]{Types.INTEGER},
                   new BeanPropertyRowMapper<>(Department.class));

           return department;
       }catch (Exception e){
           System.out.println("getDepartmentDataByID :"+e.getMessage());
           return null;
       }
    }
    public  String updateDepartment(Department department){
        String strQuerry= "Update department Set name=?  where id = ?";
        try {
            jdbcTemplate.update(strQuerry, new Object[]{department.getName(), department.getId()});
            return "Department Details updated";
        }catch (Exception e){
            System.out.println("updateDepartment: "+e.getMessage());
            return null;
        }

    }
   public String addNewDepartment(Department department){

       //check if department is available
       String strQuerry=null;
            if(department.getId()==null) {
                strQuerry = "Insert into department (name) values (?)";
                try {
                    jdbcTemplate.update(strQuerry, new Object[]{department.getName()});
                }catch (Exception e){
                    System.out.println("addNewDepartment :"+e.getMessage());
                }
            }
           else {
                strQuerry = "insert into department (id,name) values (?,?)";
                try {
                    jdbcTemplate.update(strQuerry, new Object[]{department.getId(), department.getName()});
                }catch (Exception e){
                    System.out.println("addNewDepartment :"+e.getMessage());
                }
             }
            return "New department Added";


   }
    public  String deleteRecord (int deptId){
        String strQurry ="delete from department where id=?";
      try {
          jdbcTemplate.update(strQurry, new Object[]{deptId}, new int[]{Types.INTEGER});
      }catch (Exception e)
      {
          System.out.println("deleteRecord"+ e.getMessage());
          return ("deleteRecord:"+e.getMessage());
      }
        return "Department Data Deletd";
    }

    /*
    Funtion : only whene department is newly added to the
     */
    public  Integer insertDepartment (Department department){


        KeyHolder keyHolder= new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
               if(department.getId()!=null) {
                   String strQuerry = "Insert into department (id,name) values (?,?)";
                   PreparedStatement ps = connection.prepareStatement(strQuerry, Statement.RETURN_GENERATED_KEYS);
                   ps.setInt(1, department.getId());
                   ps.setString(2, department.getName());
                   return ps;
               }else{
                   String strQuerry = "Insert into department (name) values (?)";
                   PreparedStatement ps = connection.prepareStatement(strQuerry, Statement.RETURN_GENERATED_KEYS);

                   ps.setString(1, department.getName());
                   return ps;

               }
            }
        },keyHolder);


        department.setId(keyHolder.getKey().intValue());
        return department.getId();
    }

}
