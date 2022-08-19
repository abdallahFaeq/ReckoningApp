package com.example.roomdb.database;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.roomdb.R;
import com.example.roomdb.database.model.Employee;

import java.util.List;

public class SalaryAdapter extends BaseAdapter {
    List<Employee> itemsList;
    public SalaryAdapter(List<Employee> itemsList){
        this.itemsList = itemsList;
    }

    public List<Employee> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Employee> itemsList) {
        this.itemsList = itemsList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Employee getItem(int i) {
        return itemsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return itemsList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        if (v == null){
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_spinner,null, false);
        }
        TextView txtName = v.findViewById(R.id.txt_employee_spinner);
        Employee e = getItem(i);

        txtName.setText(e.getName());
        return v;
    }
}
