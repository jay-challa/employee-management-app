package com.employeemanagement.interactor.employeelist

import com.employeemanagement.model.employeedelete.EmployeeDeleteResponse
import com.employeemanagement.model.employeelist.Data

interface EmployeeListInteractor {
    interface OnFinishedListener {
        fun getAllEmployee(data : Map<String,String>)
        fun onSuccess(data : List<Data>)
        fun onFailure(message: String)

        fun onDeleteSuccess(data : EmployeeDeleteResponse)
        fun onDeleteFail(message: String)
    }

    fun getEmployee(data: Map<String, String>, listener: OnFinishedListener)
    fun deleteEmployee(id : String, listener: OnFinishedListener)
}