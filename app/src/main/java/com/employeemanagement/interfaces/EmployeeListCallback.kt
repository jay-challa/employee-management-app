package com.employeemanagement.interfaces

import com.employeemanagement.model.employeedelete.EmployeeDeleteResponse
import com.employeemanagement.model.employeelist.Data

interface EmployeeListCallback {
    fun onSuccess(data : List<Data>)
    fun onFail(message : String)

    fun onDeleteSuccess(data : EmployeeDeleteResponse)
    fun onDeleteFail(message: String)
}