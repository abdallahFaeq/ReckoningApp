package com.example.roomdb.database.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.roomdb.database.DateConverter;

import java.io.Serializable;
import java.util.Date;
@Entity(tableName = "employee_table")
@TypeConverters({DateConverter.class})
public class Employee implements Serializable {
    // Employee {id, name, birthDate, email}
    @PrimaryKey
    @ColumnInfo(name = "emp_id")
    private long id;
    @NonNull
    private String name;
    @NonNull
    private String email;
    private Date birthDate;

    public Employee(long id, @NonNull String name, @NonNull String email, Date birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Employee() {
    }

    public Employee(int i, String abdallah, String s) {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
