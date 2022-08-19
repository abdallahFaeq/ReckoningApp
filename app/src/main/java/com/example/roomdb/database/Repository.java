package com.example.roomdb.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.roomdb.database.dao.EmployeeDao;
import com.example.roomdb.database.dao.SalaryEmployeeDao;
import com.example.roomdb.database.model.Employee;
import com.example.roomdb.database.model.SalaryEmployee;

import java.util.Date;
import java.util.List;

public class Repository {
    MyRoomDatabase db;
    EmployeeDao employeeDao;
    SalaryEmployeeDao salaryDao;

    public Repository(Application application){
        this.db = MyRoomDatabase.getInstance(application);
        this.employeeDao = db.getEmployeeDao();
        this.salaryDao = db.getSalaryEmployeeDao();
    }
    // employeeDao methods

    public void insertEmployee(Employee... employee){
      MyRoomDatabase.databaseWriteExcutor.execute(()->{
          this.employeeDao.insertEmployee(employee);
      });
    }
    public void updateEmployee(Employee... employees){
        MyRoomDatabase.databaseWriteExcutor.execute(new Runnable() {
            @Override
            public void run() {
            employeeDao.updateEmployee(employees);
            }
        });
    }
    public void deleteEmployee(Employee... employees){
        MyRoomDatabase.databaseWriteExcutor.execute(new Runnable() {
            @Override
            public void run() {
                employeeDao.deleteEmployee(employees);
            }
        });
    }
    public void deleteEmployees(String email){
        MyRoomDatabase.databaseWriteExcutor.execute(()->{
            this.employeeDao.deleteEmployees(email);
        });
    }
    LiveData<List<Employee>> getAllEmployees(){
        return employeeDao.getAllEmployees();
    }
    LiveData<List<Employee>> getAllEmployeesByEmail(String email){
        return employeeDao.getEmployeesByEmail(email);
    }
    LiveData<List<Employee>> getAllEmployeesByName(String name){
        return employeeDao.getEmployeeByName(name);
    }

    // salaryDao methods

    public void insertSalary(SalaryEmployee... salaries){
        MyRoomDatabase.databaseWriteExcutor.execute(new Runnable() {
            @Override
            public void run() {
                salaryDao.insertSalary(salaries);
            }
        });
    }
    public void updateSalary(SalaryEmployee... salaries){
        MyRoomDatabase.databaseWriteExcutor.execute(()->{
            salaryDao.updateSalary(salaries);
        });
    }
    public void deleteSalary(SalaryEmployee... salaries){
        MyRoomDatabase.databaseWriteExcutor.execute(new Runnable() {
            @Override
            public void run() {
                salaryDao.deleteSalary(salaries);
            }
        });
    }
    LiveData<List<SalaryEmployee>> getEmployeeSalaries(long fId){
        return salaryDao.getEmployeeSalaries(fId);
    }
    LiveData<List<SalaryEmployee>> getEmployeeSalaries(long fId, Date from, Date to) {

        return salaryDao.getEmployeeSalaries(fId,from,to);
    }
    LiveData<List<SalaryEmployee>> getEmployeeSalaries(Date from, Date to) {

        return salaryDao.getEmployeeSalaries(from,to);
    }
    void getSumSalaries(long emp_id, DoubleValueListener doubleValueListener) {

        MyRoomDatabase.databaseWriteExcutor.execute(new Runnable() {
            @Override
            public void run() {
                double value = salaryDao.getSumSalaries(emp_id);
                doubleValueListener.onValueSubmit(value);
            }
        });
    }
}
