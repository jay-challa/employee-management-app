package com.employeemanagement

import android.app.Application
import com.employeemanagement.room.database.EmployeeDatabase
import com.employeemanagement.room.repository.EmployeeDatasouce
import com.employeemanagement.room.repository.EmployeeRepository
import com.employeemanagement.utils.Constant.employeeDatabase
import com.employeemanagement.utils.Constant.employeeRepository

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initDatabase()
    }

    fun initDatabase() {
        employeeDatabase = EmployeeDatabase.getInstance(this)!!
        employeeRepository = EmployeeRepository.getInstance(
            EmployeeDatasouce.getInstance(
                employeeDatabase.employeeDao()
            )
        )
    }
}