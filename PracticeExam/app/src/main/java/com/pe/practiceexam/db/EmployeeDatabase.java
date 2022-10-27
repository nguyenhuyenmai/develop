/**
 * @author: LinhDQHE140751
 *
 * 2022-10-15 LinhDQ(FPT) [Ass_No15] Practice Example - Employee2
 */
package com.pe.practiceexam.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Employee.class}, version = 1, exportSchema = false)
public abstract class EmployeeDatabase extends RoomDatabase {

    private static EmployeeDatabase database;

    private static String DATABASE_NAME = "employee_db"; // database name

    /**
     * get database instance
     * @param context
     * @return database
     */
    public synchronized static EmployeeDatabase getInstance(Context context) {
        // if database null -> create new database
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(),
                            EmployeeDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    /**
     * employee database event
     */
    public abstract EmployeeDAO employeeDAO();
}
