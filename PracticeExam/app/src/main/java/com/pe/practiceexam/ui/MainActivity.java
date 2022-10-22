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
import com.pe.practiceexam.db.Student;
import com.pe.practiceexam.db.StudentAdapter;
import com.pe.practiceexam.db.StudentDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private StudentDatabase database;
    private StudentAdapter studentAdapter;
    private List<Student> studentList = new ArrayList<>();
    TextView id, fullname, birthday, average_score;
    RecyclerView recyclerView;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.item_id);
        fullname = findViewById(R.id.item_fullname);
        birthday = findViewById(R.id.item_birthday);
        average_score = findViewById(R.id.item_average_score);
        recyclerView = findViewById(R.id.recyclerView);

        database = StudentDatabase.getInstance(this);
        studentList = database.studentDAO().getAllStudents();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        studentAdapter = new StudentAdapter(MainActivity.this, studentList);
        recyclerView.setAdapter(studentAdapter);

    }

    private boolean BooleanStudentExist(Student student){
        studentList.addAll(database.studentDAO().getAllStudents());
        for (Student s : studentList) {
            if (s.getId() == student.getId()) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValidDate(String input) {
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

    private String ValidInputstudent(){
        String message = "1";

        String sId = id.getText().toString().trim();
        String sFullName = fullname.getText().toString().trim();
        String sBirthday = birthday.getText().toString().trim();
        String sAverageScore = average_score.getText().toString().trim();

        if(sId.equals("")){
            id.setError("Enter id number.", null);
        }
        if(sFullName.equals("")){
            fullname.setError("Enter fullname.", null);
        }

        if(sBirthday.equals("")){
            birthday.setError("Enter birthday **/**/****", null);
        }

        if(sAverageScore.equals("")){
            average_score.setError("Enter score.", null);
        }

        if(!isValidDate(sBirthday)) {
            birthday.setError("Date not exist.", null);
            return "";
        }

        if(!sAverageScore.equals("")){
            float fAverageScore = Float.parseFloat(sAverageScore);
            if(fAverageScore > 10 || fAverageScore < 0){
                average_score.setError("Average score from 0 to 10.", null);
                return "";
            }
        }

        if(sId.equals("") && sFullName.equals("") && sBirthday.equals("") && sAverageScore.equals("")){
            return "";
        }
        return message;
    }

    private String ValidInputSearch(){
        String message = "1";
        String sId = id.getText().toString().trim();
        String sFullName = fullname.getText().toString().trim();

        if(sId.equals("") && sFullName.equals("")){
            return "";
        }
        return message;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addStudent(View view) {
        if (ValidInputstudent().equals("")){
            return;
        }
        int sId= Integer.parseInt(id.getText().toString().trim());
        String sFullName = fullname.getText().toString().trim();
        String sBirthday = birthday.getText().toString().trim();
        float sAverageScore = Float.parseFloat(average_score.getText().toString().trim());

        Student addStudent = new Student(sId, sFullName, sBirthday, sAverageScore);

        database.studentDAO().insertStudent(addStudent);
        studentList.clear();
        studentList.addAll(database.studentDAO().getAllStudents());
        studentAdapter.notifyDataSetChanged();

        id.setText("");
        fullname.setText("");
        birthday.setText("");
        average_score.setText("");
    }


    @SuppressLint("NotifyDataSetChanged")
    public void deleteStudent(View view) {
        if (ValidInputstudent().equals("")){
            return;
        }
        int sId= Integer.parseInt(id.getText().toString().trim());
        String sFullName = fullname.getText().toString().trim();
        String sBirthday = birthday.getText().toString().trim();
        float fAverageScore = Float.parseFloat(average_score.getText().toString().trim());

        Student student = new Student(sId, sFullName, sBirthday, fAverageScore);

        if(BooleanStudentExist(student)){
            database.studentDAO().deleteStudent(student);
            studentList.clear();
            studentList.addAll(database.studentDAO().getAllStudents());
            studentAdapter.notifyDataSetChanged();

            id.setText("");
            fullname.setText("");
            birthday.setText("");
            average_score.setText("");
        }else {
            Toast.makeText(MainActivity.this,"ID not exist, to delete", Toast.LENGTH_LONG).show();
            studentList.clear();
            studentList.addAll(database.studentDAO().getAllStudents());
            studentAdapter.notifyDataSetChanged();

            id.setText("");
            fullname.setText("");
            birthday.setText("");
            average_score.setText("");
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateStudent(View view) {
        if (ValidInputstudent().equals("")){
            return;
        }
        int sId= Integer.parseInt(id.getText().toString().trim());
        String sFullName = fullname.getText().toString().trim();
        String sBirthday = birthday.getText().toString().trim();
        float fAverageScore = Float.parseFloat(average_score.getText().toString().trim());

        Student student = new Student(sId, sFullName, sBirthday, fAverageScore);

        if(BooleanStudentExist(student)){
            database.studentDAO().updateStudent(sId, sFullName, sBirthday, fAverageScore);
            studentList.clear();
            studentList.addAll(database.studentDAO().getAllStudents());
            studentAdapter.notifyDataSetChanged();

            id.setText("");
            fullname.setText("");
            birthday.setText("");
            average_score.setText("");
        }else {
            Toast.makeText(MainActivity.this,"ID not exist.", Toast.LENGTH_LONG).show();

            studentList.clear();
            studentList.addAll(database.studentDAO().getAllStudents());
            studentAdapter.notifyDataSetChanged();

            id.setText("");
            fullname.setText("");
            birthday.setText("");
            average_score.setText("");
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void searchStudents(View view) {
        if (ValidInputSearch().equals("")) {
            studentList.clear();
            studentList = database.studentDAO().getAllStudents();
            studentAdapter = new StudentAdapter(MainActivity.this, studentList);
            recyclerView.setAdapter(studentAdapter);
            return;
        }

        int iId = 0;
        try {
            iId = Integer.parseInt(id.getText().toString().trim());
        } catch (Exception exception) {
            iId = -1;
        }
        String sFullName = fullname.getText().toString().trim();

        if(sFullName.equals("")){
            studentList = database.studentDAO().searchStudentID(iId);
            studentAdapter = new StudentAdapter(MainActivity.this, studentList);
            recyclerView.setAdapter(studentAdapter);
            return;
        }
        studentList = database.studentDAO().searchStudentsIDandName(iId, sFullName);
        studentAdapter = new StudentAdapter(MainActivity.this, studentList);
        recyclerView.setAdapter(studentAdapter);

    }
}