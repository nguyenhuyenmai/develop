/**
 * @author: LinhDQHE140751
 *
 * 2022-10-15 LinhDQ(FPT) [Ass_No15] Practice Example - Employee2
 */
package com.pe.practiceexam.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "employee_db") // Database table
public class Employee implements Serializable {
    @PrimaryKey
    private int id;             // employee's ID
    @ColumnInfo
    private String fullname;    // employee's name
    @ColumnInfo
    private String hireDate;    // employee's name
    @ColumnInfo
    private double salary;      // employee's name

    /**
     * employee constructor
     */
    public Employee() {
    }

    /**
     * employee constructor
     *
     * @param id
     * @param fullname
     * @param hireDate
     * @param salary
     */
    public Employee(int id, String fullname, String hireDate, double salary) {
        this.id = id;
        this.fullname = fullname;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    /**
     * get employee id
     */
    public int getId() {
        return id;
    }

    /**
     * set employee id
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * get employee fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * set employee fullname
     *
     * @param fullname
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * get employee hiredate
     */
    public String getHireDate() {
        return hireDate;
    }

    /**
     * set employee hiredate
     *
     * @param hireDate
     */
    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    /**
     * get employee salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * set employee salary
     *
     * @param salary
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }
}
