package com.pe.practiceexam.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "student_db")
public class Student implements Serializable {
    @PrimaryKey
    private int id;
    @ColumnInfo
    private String fullname;
    @ColumnInfo
    private String birthday;
    @ColumnInfo
    private float average_score;

    public Student(){

    }

    public Student(int id, String fullname, String birthday, float average_score) {
        this.id = id;
        this.fullname = fullname;
        this.birthday = birthday;
        this.average_score = average_score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public float getAverage_score() {
        return average_score;
    }

    public void setAverage_score(float average_score) {
        this.average_score = average_score;
    }
}
