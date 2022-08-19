package com.example.roomdb.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.example.roomdb.database.DateConverter;
import com.example.roomdb.database.model.SalaryEmployee;

import java.util.Date;
import java.util.List;

@Dao
@TypeConverters({DateConverter.class})
public interface SalaryEmployeeDao {
    @Insert
    void insertSalary(SalaryEmployee... salaries);
    @Update
    void updateSalary(SalaryEmployee... salaries);
    @Delete
    void deleteSalary(SalaryEmployee... salaries);

    @Query("select * from SalaryEmployee where empId = :fId order by date desc")
    LiveData<List<SalaryEmployee>> getEmployeeSalaries(long fId);

    @Query("select * from SalaryEmployee where empId = :fId AND date>=:from AND date<= :to order by date desc")
    LiveData<List<SalaryEmployee>> getEmployeeSalaries(long fId, Date from, Date to);

    // search about salary from date to date
    @Query("select * from SalaryEmployee where date>=:from AND date<=:to ")
    LiveData<List<SalaryEmployee>> getEmployeeSalaries(Date from, Date to);

    @Query("select sum(amount) from salaryemployee where empId=:emp_id")
    double getSumSalaries(long emp_id);

}
