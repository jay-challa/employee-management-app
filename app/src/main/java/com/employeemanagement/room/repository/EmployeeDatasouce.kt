package com.employeemanagement.room.repository

import com.employeemanagement.room.dao.EmployeeDao
import com.employeemanagement.room.entity.Employee

class EmployeeDatasouce(val employeeDao: EmployeeDao) : IEmployeeRepository {
    override fun getEmployeeList(): List<Employee> {
        return employeeDao.getAll()
    }

    override fun getEmployeeById(id: Int): List<Employee> {
        return employeeDao.getEmployeeById(id)
    }

    override fun updateEmployee(id: Int, name :String,age : String,salary : String) {
        employeeDao.update(id,name,age,salary)
    }

    override fun saveEmployee(vararg data: Employee) {
        employeeDao.insert(*data)
    }

    override fun deleteEmployee(vararg data: Employee?) {
        employeeDao.delete(*data)
    }

    companion object {
        private var instance: EmployeeDatasouce? = null
        fun getInstance(contentDetailDao: EmployeeDao): EmployeeDatasouce {
            if (instance == null) {
                instance = EmployeeDatasouce(contentDetailDao)
            }
            return instance as EmployeeDatasouce
        }
    }
}