package com.example.roomdb.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.roomdb.MainActivity;
import com.example.roomdb.database.model.Employee;
import com.example.roomdb.databinding.ActivityAddEmployeeBinding;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class AddEmployeeActivity extends AppCompatActivity {
    public static final String EXTRA_EMPLOYEE = "extra_employee";
    private ActivityAddEmployeeBinding binding;
    Calendar selectedTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEmployeeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnSaveEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.textName.getText().toString();
                String strId = binding.textId.getText().toString();
                String email = binding.textEmail.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(strId) || TextUtils.isEmpty(email) || selectedTime == null){
                    Toast.makeText(AddEmployeeActivity.this, "please enter a valid data", Toast.LENGTH_SHORT).show();
                    return;
                }
                long empId = Long.parseLong(strId);
                Employee employee = new Employee(empId,name,email,selectedTime.getTime());

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra(EXTRA_EMPLOYEE,employee);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        binding.btnDateEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        selectedTime = Calendar.getInstance();
                        selectedTime.set(Calendar.YEAR,year);
                        selectedTime.set(Calendar.MONTH,monthOfYear);
                        selectedTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    }
                },Calendar.getInstance());
                dialog.show(getSupportFragmentManager(),"");
            }
        });
    }
}