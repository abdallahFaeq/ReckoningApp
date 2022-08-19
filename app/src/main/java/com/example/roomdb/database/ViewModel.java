package com.example.roomdb.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomdb.database.model.Employee;
import com.example.roomdb.database.model.SalaryEmployee;

import java.util.Date;
import java.util.List;

public class ViewModel extends AndroidViewModel {
    Repository repository;
    public ViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
    }
    // employeeDao methods

    public void insertEmployee(Employee... employee){
        repository.insertEmployee(employee);
    }
    public void updateEmployee(Employee... employees){
       repository.updateEmployee(employees);
    }
    public void deleteEmployee(Employee... employees){
        repository.deleteEmployee(employees);
    }
    public void deleteEmployees(String email){
        repository.deleteEmployees(email);
    }
    public LiveData<List<Employee>> getAllEmployees(){
        return repository.getAllEmployees();
    }
    public LiveData<List<Employee>> getAllEmployeesByEmail(String email){
        return repository.getAllEmployeesByEmail(email);
    }
    public LiveData<List<Employee>> getAllEmployeesByName(String name){
        return repository.getAllEmployeesByName(name);
    }

    // salaryEmployee
    public void insertSalary(SalaryEmployee... salaries){
         repository.insertSalary(salaries);
    }
    public void updateSalary(SalaryEmployee... salaries){
       repository.updateSalary(salaries);
    }
    public void deleteSalary(SalaryEmployee... salaries){
       repository.deleteSalary(salaries);
    }
    public LiveData<List<SalaryEmployee>> getEmployeeSalaries(long fId){
        return repository.getEmployeeSalaries(fId);
    }
    public LiveData<List<SalaryEmployee>> getEmployeeSalaries(long fId, Date from, Date to) {

        return repository.getEmployeeSalaries(fId,from,to);
    }
    public LiveData<List<SalaryEmployee>> getEmployeeSalaries(Date from, Date to) {

        return repository.getEmployeeSalaries(from,to);
    }

    public void getSumSalaries(long emp_id, DoubleValueListener doubleValueListener) {
        repository.getSumSalaries(emp_id,doubleValueListener);
    }
}
