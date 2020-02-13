package com.employeemanagement.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.employeemanagement.room.dao.EmployeeDao
import com.employeemanagement.room.entity.Employee
import dev.matrix.roomigrant.GenerateRoomMigrations

@Database(entities = arrayOf(Employee::class), version = 1, exportSchema = false)
@GenerateRoomMigrations
abstract class EmployeeDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao

    companion object {
        private var instance: EmployeeDatabase? = null

        fun getInstance(context: Context): EmployeeDatabase? {

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    EmployeeDatabase::class.java,
                    "employee.sqlite"
                ).allowMainThreadQueries(

                ).build()
            }
            return instance
        }
    }
}