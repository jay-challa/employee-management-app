package com.employeemanagement.presenter.employeecreate

interface EmployeeCreatePresenter {
    fun saveEmployee(data: Map<String, String>)
    fun updateEmployee(id: String, data: Map<String, String>)
}