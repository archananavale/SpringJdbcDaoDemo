package com.jdbcDaoDemo.SpringJdbcDaoDemo.Model;

import lombok.Getter;
import lombok.Setter;

/*
class Employee :
is the model of database table
 */
// bellow anNotation provided by lombok
@Getter
@Setter
public class Employee {
    int id;
    String name;
    String city;
    Department department;
}
