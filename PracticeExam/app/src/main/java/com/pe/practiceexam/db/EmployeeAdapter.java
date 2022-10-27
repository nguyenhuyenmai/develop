/**
 * @author: LinhDQHE140751
 *
 * 2022-10-15 LinhDQ(FPT) [Ass_No15] Practice Example - Employee2
 */
package com.pe.practiceexam.db;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pe.practiceexam.R;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {
    private List<Employee> employeeList;    // employee list
    private Activity context;               // activity
    private EmployeeDatabase database;      // database

    /**
     * Employee management Adapter
     * @param context
     * @param employeeList
     */
    @SuppressLint("NotifyDataSetChanged")
    public EmployeeAdapter(Activity context, List<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
        notifyDataSetChanged();
    }

    /**
     * create view
     * @param parent
     * @param viewType
     * @return view
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_employee, parent, false);
        return new ViewHolder(view);
    }

    /**
     * show view position
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Employee employee = employeeList.get(position);

        database = EmployeeDatabase.getInstance(context);

        holder.id.setText(String.valueOf(employee.getId()));
        holder.fullname.setText(employee.getFullname());
        holder.hireDate.setText(employee.getHireDate());
        holder.salary.setText(String.valueOf(employee.getSalary()));
    }

    /**
     * get employee list size
     * @return employee list size
     */
    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    /**
     * View employee
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView id, fullname, hireDate, salary;

        @SuppressLint("CutPasteId")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.item_id);
            fullname = itemView.findViewById(R.id.item_fullname);
            hireDate = itemView.findViewById(R.id.item_hireDate);
            salary = itemView.findViewById(R.id.item_salary);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView edt_id = context.findViewById(R.id.item_id);
                    TextView edt_fullname = context.findViewById(R.id.item_fullname);
                    TextView edt_hireDate = context.findViewById(R.id.item_hireDate);
                    TextView edt_salary = context.findViewById(R.id.item_salary);

                    edt_id.setText(id.getText().toString());
                    edt_fullname.setText(fullname.getText().toString());
                    edt_hireDate.setText(hireDate.getText().toString());
                    edt_salary.setText(salary.getText().toString());
                }
            });
        }
    }
}
