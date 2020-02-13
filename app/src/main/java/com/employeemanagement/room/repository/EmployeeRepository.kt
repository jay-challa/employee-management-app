package com.employeemanagement.room.repository

import com.employeemanagement.room.entity.Employee

class EmployeeRepository(val iEmployeeRepository: IEmployeeRepository) : IEmployeeRepository {
    override fun getEmployeeList(): List<Employee> {
        return iEmployeeRepository.getEmployeeList()
    }

    override fun getEmployeeById(id: Int): List<Employee> {
        return iEmployeeRepository.getEmployeeById(id)
    }

    override fun saveEmployee(vararg data: Employee) {
        iEmployeeRepository.saveEmployee(*data)
    }

    override fun updateEmployee(id: Int, name: String, age: String, salary: String) {
        iEmployeeRepository.updateEmployee(id, name, age, salary)
    }

    override fun deleteEmployee(vararg data: Employee?) {
        iEmployeeRepository.deleteEmployee(*data)
    }

    companion object {
        private var instance: EmployeeRepository? = null

        fun getInstance(iContentRepository: IEmployeeRepository): EmployeeRepository {
            if (instance == null) {
                instance = EmployeeRepository(iContentRepository)
            }
            return instance as EmployeeRepository
        }
    }
}