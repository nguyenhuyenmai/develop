package com.pe.practiceexam.db;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDAO {


    @Insert(onConflict = REPLACE)
    void insertStudent(Student student);

    @Query("UPDATE student_db SET fullname = :sfullname, birthday = :sbirthday,average_score = :faverage_score WHERE id = :id")
    void updateStudent(int id, String sfullname, String sbirthday, float faverage_score);

    @Delete
    void resetListStudent(List<Student> listStdent);

    @Delete
    void deleteStudent(Student student);

    @Query("SELECT * FROM student_db")
    List<Student> getAllStudents();

    @Query("SELECT * FROM student_db WHERE id = :iid or fullname  LIKE '%' || :fullname || '%'")
    List<Student> searchStudentsIDandName(int iid, String fullname);

    @Query("SELECT * FROM student_db WHERE id = :iid" )
    List<Student> searchStudentID(int iid);
}
