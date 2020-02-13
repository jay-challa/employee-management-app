package com.employeemanagement.interactor.employeecreate

import com.employeemanagement.model.employeecreate.EmployeeCreateResponse
import com.employeemanagement.model.employeeupdate.EmployeeUpdateResponse


interface EmployeeCreateIInteractor {
    interface OnFinishedListener{

        fun onSuccess(status : EmployeeCreateResponse)
        fun onFailure(message: String)

        fun onUpdateSuccess(data : EmployeeUpdateResponse)
        fun onUpdateFailure(message: String)

    }


    fun saveEmployee(data : Map<String,String>,listner : OnFinishedListener)
    fun updateEmployee(id : String,data : Map<String,String>,listner : OnFinishedListener)
}