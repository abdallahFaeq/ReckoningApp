package com.example.roomdb.database.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.roomdb.database.DateConverter;

import java.util.Date;

@Entity(foreignKeys = {@ForeignKey(entity = Employee.class, parentColumns = "emp_id",
childColumns = "empId",onUpdate = ForeignKey.CASCADE,onDelete = ForeignKey.CASCADE)})
@TypeConverters({DateConverter.class})
public class SalaryEmployee {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @NonNull
    private double amount;
    @NonNull
    private long empId;
    @NonNull
    private Date date;


    public SalaryEmployee(long id, double amount, long empId, @NonNull Date date) {
        this.id = id;
        this.amount = amount;
        this.empId = empId;
        this.date = date;
    }
    public SalaryEmployee( double amount, long empId, @NonNull Date date) {
        this.amount = amount;
        this.empId = empId;
        this.date = date;
    }

    public SalaryEmployee() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    @NonNull
    public Date getDate() {
        return date;
    }

    public void setDate(@NonNull Date date) {
        this.date = date;
    }
}
