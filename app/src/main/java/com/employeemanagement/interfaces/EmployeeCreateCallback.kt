package com.employeemanagement.interfaces

import com.employeemanagement.model.employeecreate.EmployeeCreateResponse
import com.employeemanagement.model.employeeupdate.EmployeeUpdateResponse

interface EmployeeCreateCallback {
    fun onSucsess(status: EmployeeCreateResponse)
    fun onFail(message: String)

    fun onUpdateSuccess(data : EmployeeUpdateResponse)
    fun onUpdateFail(message: String)
}