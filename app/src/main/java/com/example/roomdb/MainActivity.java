package com.example.roomdb;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.roomdb.database.AddEmployeeActivity;
import com.example.roomdb.database.AddSalaryActivity;
import com.example.roomdb.database.model.Employee;
import com.example.roomdb.database.SalariesEmployeesRecyclerAdapter;
import com.example.roomdb.database.ViewModel;
import com.example.roomdb.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    ViewModel viewModel;
    SalariesEmployeesRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // create object from view model with this way
        /*ViewModel viewModel = new ViewModelProvider(this)
                .get(ViewModel.class);*/


        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        ActivityResultLauncher<Intent> arl = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null){
                    Employee employee = (Employee) result.getData().getSerializableExtra(AddEmployeeActivity.EXTRA_EMPLOYEE);
                    viewModel.insertEmployee(employee);
                }
            }
        });
        binding.fabShowEmployeeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AddEmployeeActivity.class);
                arl.launch(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_show_salary_activity,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.show_salary_activity){
            Intent intent = new Intent(this, AddSalaryActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    void populateDataToRecyclerView(List<Employee> employees){
        adapter = new SalariesEmployeesRecyclerAdapter(employees, viewModel);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Employee> itemsList = new ArrayList<>();
        populateDataToRecyclerView(itemsList);

        viewModel.getAllEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                adapter.setEmployeesList(employees);
            }
        });

    }


}