package com.example.roomdb.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.roomdb.database.model.Employee;
import com.example.roomdb.database.model.SalaryEmployee;
import com.example.roomdb.databinding.ActivityAddSalaryBinding;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddSalaryActivity extends AppCompatActivity {
    private ActivityAddSalaryBinding binding;
    Calendar selectedDate;
    ViewModel viewModel;
    SalaryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddSalaryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider(this).get(ViewModel.class);


        adapter = new SalaryAdapter(new ArrayList<>());
        binding.spinnerSalary.setAdapter(adapter);

        viewModel.getAllEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                adapter.setItemsList(employees);
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sAmount = binding.textAmountSalary.getText().toString();
                long emId = binding.spinnerSalary.getSelectedItemId();
                if (TextUtils.isEmpty(sAmount) || selectedDate == null){
                    Toast.makeText(AddSalaryActivity.this, "please enter a valid data", Toast.LENGTH_SHORT).show();
                    return;
                }
                double amount = Double.parseDouble(sAmount);
                SalaryEmployee salaryEmployee = new SalaryEmployee(amount, emId,selectedDate.getTime());

                viewModel.insertSalary(salaryEmployee);
                finish();
            }
        });

        binding.btnDateSalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        selectedDate = Calendar.getInstance();
                        selectedDate.set(Calendar.YEAR,year);
                        selectedDate.set(Calendar.MONTH,monthOfYear);
                        selectedDate.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                    }
                },Calendar.getInstance());
                dialog.show(getSupportFragmentManager(),"");
            }
        });




    }
}