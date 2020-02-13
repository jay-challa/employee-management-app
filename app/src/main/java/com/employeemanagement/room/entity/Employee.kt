package com.employeemanagement.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Employee")
class Employee {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int =0

    @ColumnInfo(name = "employee_name")
    var employee_name : String? = null

    @ColumnInfo(name = "employee_age")
    var employee_age : String? = null

    @ColumnInfo(name = "employee_salary")
    var employee_salary : String? = null

    @ColumnInfo(name = "profile_image")
    var profile_image : String? = null
}