package com.example.roomdb.database;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdb.R;
import com.example.roomdb.database.model.Employee;
import com.example.roomdb.databinding.ItemEmployeeSalaryBinding;

import java.util.List;

public class SalariesEmployeesRecyclerAdapter extends RecyclerView.Adapter<SalariesEmployeesRecyclerAdapter.SalariesEmployeesHolder>{
    List<Employee> employeesList;
    ViewModel viewModel;
    double value;
    public SalariesEmployeesRecyclerAdapter(List<Employee> employeesList, ViewModel viewModel){
        this.employeesList = employeesList;
        this.viewModel = viewModel;
    }
    @NonNull
    @Override
    public SalariesEmployeesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SalariesEmployeesHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee_salary,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SalariesEmployeesHolder holder, int position) {
        Employee employee = employeesList.get(position);

        holder.binding.txtNameItem.setText(employee.getName());
         viewModel.getSumSalaries(employee.getId(), new DoubleValueListener() {
            @Override
            public void onValueSubmit(Double value) {
                holder.binding.txtSalaryItem.setText(String.valueOf(value));
            }
        });
    }

    public void setEmployeesList(List<Employee> employeesList) {
        this.employeesList = employeesList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return employeesList.size();
    }

    class SalariesEmployeesHolder extends RecyclerView.ViewHolder{
        ItemEmployeeSalaryBinding binding;
        public SalariesEmployeesHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemEmployeeSalaryBinding.bind(itemView);
        }
    }
}
