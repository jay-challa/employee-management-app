package com.employeemanagement.room.repository

import com.employeemanagement.room.entity.Employee

interface IEmployeeRepository {
    fun getEmployeeList(): List<Employee>
    fun getEmployeeById(id: Int): List<Employee>
    fun saveEmployee(vararg data: Employee)
    fun deleteEmployee(vararg data: Employee?)
    fun updateEmployee(id: Int, name :String,age : String,salary : String)
}