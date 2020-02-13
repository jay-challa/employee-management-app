package com.employeemanagement.presenter.employeelist

interface EmployeeListPresenter {
    fun getAllEmployee(data : Map<String,String>)
    fun deleteEmployee(id : String)


}