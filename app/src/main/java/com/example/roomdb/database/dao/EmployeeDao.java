package com.example.roomdb.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.example.roomdb.database.DateConverter;
import com.example.roomdb.database.model.Employee;

import java.util.List;

// عباره عن object من خلاله تقدر توصل للبيانات اللي موجوده ف الداتابيز :data access object<<DAO
@Dao
@TypeConverters({DateConverter.class})
public interface EmployeeDao {
    @Insert
    void insertEmployee(Employee... employee);
    @Update
    void updateEmployee(Employee... employees);
    @Delete
    void deleteEmployee(Employee... employees);

    @Query("delete from employee_table where email= :email")
     void deleteEmployees(String email);

    @Query("select * from employee_table order by name asc")
    LiveData<List<Employee>> getAllEmployees();

    @Query("select * from employee_table where email =:email")
    LiveData<List<Employee>> getEmployeesByEmail(String email);

    // like search
    @Query("select * from employee_table where name like '%'||:name||'%'")
    LiveData<List<Employee>> getEmployeeByName(String name);


}
