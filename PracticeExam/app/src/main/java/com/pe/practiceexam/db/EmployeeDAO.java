/**
 * @author: LinhDQHE140751
 *
 * 2022-10-15 LinhDQ(FPT) [Ass_No15] Practice Example - Employee2
 */
package com.pe.practiceexam.db;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmployeeDAO {

    /**
     * insert employee
     * @param employee
     */
    @Insert(onConflict = REPLACE)
    void insertEmployee(Employee employee);

    /**
     * update employee
     * @param id
     * @param fullname
     * @param hireDate
     * @param salary
     */
    @Query("UPDATE employee_db SET fullname = :fullname, hireDate = :hireDate,salary = :salary WHERE id = :id")
    void updateEmployee(int id, String fullname, String hireDate, double salary);

    /**
     * delete employee
     * @param employee
     */
    @Delete
    void deleteEmployee(Employee employee);

    /**
     * get all employee
     */
    @Query("SELECT * FROM employee_db")
    List<Employee> getAllEmployees();

    /**
     * search employee by name
     * @param employeeId
     * @param employeeName
     */
    @Query("SELECT * FROM employee_db WHERE id = :employeeId OR fullname LIKE '%' || :employeeName || '%'")
    List<Employee> searchEmployeeByIdOrName(String employeeId, String employeeName);

    /**
     * search employee by id
     * @param employeeId
     */
    @Query("SELECT * FROM employee_db WHERE id = :employeeId")
    List<Employee> searchEmployeeById(String employeeId);
}
