/**
 * @author: LinhDQHE140751
 *
 * 2022-10-15 LinhDQ(FPT) [Ass_No15] Practice Example - Employee2
 */
package com.pe.practiceexam.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.CalendarConstraints;
import com.pe.practiceexam.R;
import com.pe.practiceexam.db.Employee;
import com.pe.practiceexam.db.EmployeeAdapter;
import com.pe.practiceexam.db.EmployeeDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EmployeeDatabase database;          // database
    private EmployeeAdapter employeeAdapter;    // adapter
    private List<Employee> employeeList = new ArrayList<>();    // employee list
    TextView id, fullname, hireDate, salary;    // employee information: id, fullname, hireDate, salary
    RecyclerView recyclerView;                  // employee management view

    /**
     * create employee management activity
     *
     * @param savedInstanceState
     */
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.item_id);
        fullname = findViewById(R.id.item_fullname);
        hireDate = findViewById(R.id.item_hireDate);
        salary = findViewById(R.id.item_salary);
        recyclerView = findViewById(R.id.recyclerView);

        database = EmployeeDatabase.getInstance(this);
        employeeList = database.employeeDAO().getAllEmployees();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        employeeAdapter = new EmployeeAdapter(MainActivity.this, employeeList);
        recyclerView.setAdapter(employeeAdapter);
    }

    /**
     * add employee
     *
     * @param view
     */
    @SuppressLint("NotifyDataSetChanged")
    public void addEmployee(View view) {
        if (!ValidInputEmployee()) {
            return;
        }
        int employeeId = Integer.parseInt(id.getText().toString().trim());
        String employeeName = fullname.getText().toString().trim();
        String employeeHireDate = hireDate.getText().toString().trim();
        double employeeSalary = Double.parseDouble(salary.getText().toString().trim());

        Employee employee = new Employee(employeeId, employeeName, employeeHireDate, employeeSalary);

        database.employeeDAO().insertEmployee(employee);
        Toast.makeText(MainActivity.this, "Add Successful", Toast.LENGTH_LONG).show();
        employeeList.clear();
        employeeList.addAll(database.employeeDAO().getAllEmployees());
        employeeAdapter.notifyDataSetChanged();

        id.setText("");
        fullname.setText("");
        hireDate.setText("");
        salary.setText("");
    }

    /**
     * update employee
     *
     * @param view
     */
    @SuppressLint("NotifyDataSetChanged")
    public void updateEmployee(View view) {
        if (!ValidInputEmployee()) {
            return;
        }
        int employeeId = Integer.parseInt(id.getText().toString().trim());
        String employeeName = fullname.getText().toString().trim();
        String employeeHireDate = hireDate.getText().toString().trim();
        double employeeSalary = Double.parseDouble(salary.getText().toString().trim());

        Employee employee = new Employee(employeeId, employeeName, employeeHireDate, employeeSalary);
        // if ID exists -> update
        if (checkEmployeeExist(employee)) {
            //update employee
            database.employeeDAO().updateEmployee(employeeId, employeeName, employeeHireDate, employeeSalary);
            Toast.makeText(MainActivity.this, "Update Successful", Toast.LENGTH_LONG).show();
            employeeList.clear();
            employeeList.addAll(database.employeeDAO().getAllEmployees());
            employeeAdapter.notifyDataSetChanged();

            id.setText("");
            fullname.setText("");
            hireDate.setText("");
            salary.setText("");
        } else {
            Toast.makeText(MainActivity.this, "Employee ID not exist.", Toast.LENGTH_LONG).show();
            employeeList.clear();
            employeeList.addAll(database.employeeDAO().getAllEmployees());
            employeeAdapter.notifyDataSetChanged();

            id.setText("");
            fullname.setText("");
            hireDate.setText("");
            salary.setText("");
        }
    }

    /**
     * delete employee
     *
     * @param view
     */
    @SuppressLint("NotifyDataSetChanged")
    public void deleteEmployee(View view) {
        if (!ValidInputEmployee()) {
            return;
        }
        int employeeId = Integer.parseInt(id.getText().toString().trim());
        String employeeName = fullname.getText().toString().trim();
        String employeeHireDate = hireDate.getText().toString().trim();
        double employeeSalary = Double.parseDouble(salary.getText().toString().trim());

        Employee employee = new Employee(employeeId, employeeName, employeeHireDate, employeeSalary);

        // if ID exists -> delete
        if (checkEmployeeExist(employee)) {
            // delete employee
            database.employeeDAO().deleteEmployee(employee);
            Toast.makeText(MainActivity.this, "Delete Successful", Toast.LENGTH_LONG).show();
            employeeList.clear();
            employeeList.addAll(database.employeeDAO().getAllEmployees());
            employeeAdapter.notifyDataSetChanged();

            id.setText("");
            fullname.setText("");
            hireDate.setText("");
            salary.setText("");
        } else {
            Toast.makeText(MainActivity.this, "Employee ID not exist.", Toast.LENGTH_LONG).show();
            employeeList.clear();
            employeeList.addAll(database.employeeDAO().getAllEmployees());
            employeeAdapter.notifyDataSetChanged();

            id.setText("");
            fullname.setText("");
            hireDate.setText("");
            salary.setText("");
        }
    }

    /**
     * search employees
     *
     * @param view
     */
    @SuppressLint("NotifyDataSetChanged")
    public void searchEmployees(View view) {
        // if id and name empty -> search all employees
        if (!ValidInputSearch()) {
            employeeList.clear();
            employeeList = database.employeeDAO().getAllEmployees();
            employeeAdapter = new EmployeeAdapter(MainActivity.this, employeeList);
            recyclerView.setAdapter(employeeAdapter);
            return;
        }

        String employeeName = fullname.getText().toString().trim();
        String employeeIdSearch = id.getText().toString().trim();

        // search employees by ID
        if (employeeName.equals("")) {
            employeeList = database.employeeDAO().searchEmployeeById(employeeIdSearch);
            employeeAdapter = new EmployeeAdapter(MainActivity.this, employeeList);
            recyclerView.setAdapter(employeeAdapter);
            return;
            // search employees by ID or Name
        } else {
            employeeList = database.employeeDAO().searchEmployeeByIdOrName(employeeIdSearch, employeeName);
            employeeAdapter = new EmployeeAdapter(MainActivity.this, employeeList);
            recyclerView.setAdapter(employeeAdapter);
        }
    }

    /**
     * check employee input
     *
     * @return
     */
    private Boolean ValidInputEmployee() {
        String employeeId = id.getText().toString().trim();
        String employeeName = fullname.getText().toString().trim();
        String employeeHireDate = hireDate.getText().toString().trim();
        String employeeSalary = salary.getText().toString().trim();

        // if ID empty -> error
        if (employeeId.equals("")) {
            id.setError("Enter employeeID", null);
        }
        // if name empty -> error
        if (employeeName.equals("")) {
            fullname.setError("Enter employee's name", null);
        }
        // if hireDate empty -> error
        if (employeeHireDate.equals("")) {
            hireDate.setError("Enter employee's hireDate (dd/mm/yyyy)", null);
        }
        // if salary empty -> error
        if (employeeSalary.equals("")) {
            salary.setError("Enter employee's salary", null);
        }
        // if empty -> false
        if (employeeId.equals("") || employeeName.equals("") || employeeHireDate.equals("") || employeeSalary.equals("")) {
            return false;
        }
        try {
            int idCheck = Integer.parseInt(employeeId);
        } catch (NumberFormatException exception) {
            id.setError("ID must be integer");
            return false;
        }
        // if hireDate wrong format (dd/MM/yyyy) -> false
        if (!validHireDate(employeeHireDate)) {
            hireDate.setError("Please enter hireDate (dd/MM/yyyy)");
            return false;
        }
        // if salary <= 0 -> false
        if (employeeSalary.equals(".") || (!employeeSalary.equals("") && Double.parseDouble(employeeSalary) <= 0.0)) {
            salary.setError("Salary must be double and salary > 0");
            return false;
        }

        return true;
    }

    /**
     * check input search
     *
     * @return
     */
    private Boolean ValidInputSearch() {
        String employeeId = id.getText().toString().trim();
        String employeeName = fullname.getText().toString().trim();
        if (employeeId.equals("") && employeeName.equals("")) {
            return false;
        }
        return true;
    }

    /**
     * check employee exist
     *
     * @param employee
     * @return existed: true / new: false
     */
    private boolean checkEmployeeExist(Employee employee) {
        employeeList.clear();
        employeeList.addAll(database.employeeDAO().getAllEmployees());
        for (Employee e : employeeList) {
            // if employee ID exists
            if (e.getId() == employee.getId()) {
                return true;
            }
        }
        return false;
    }

    /**
     * check date format (dd/MM/yyyy)
     *
     * @param input
     * @return true: (dd/MM/yyyy) / false: else
     */
    private static boolean validHireDate(String input) {
        String formatString = "dd/MM/yyyy";
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatString);
            format.setLenient(false);
            format.parse(input);
        } catch (ParseException | IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}