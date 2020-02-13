package com.employeemanagement.room.dao

import androidx.room.*
import com.employeemanagement.room.entity.Employee


@Dao
interface EmployeeDao {
    @Query("SELECT * FROM Employee")
    fun getAll(): List<Employee>

    @Query("SELECT * FROM Employee WHERE id = :ids ")
    fun getEmployeeById(ids: Int): List<Employee>

    @Insert
    fun insert(vararg users: Employee)

    @Query("UPDATE Employee SET employee_name=:name and employee_age = :age and employee_salary = :salary WHERE id= :ids")
    fun update(ids: Int, name: String, age: String, salary: String)

    @Delete
    fun delete(vararg user: Employee?)
}