package com.pe.practiceexam.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {

    // Tạo database instance
    private static StudentDatabase database;

    private static String DATABASE_NAME = "student_db";

    public synchronized static StudentDatabase getInstance(Context context){
        // Nếu databe chưa có thì khởi tạo
        if (database == null){
            database = Room.databaseBuilder(context.getApplicationContext(),
                            StudentDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract StudentDAO studentDAO();
}
