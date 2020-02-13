package com.employeemanagement.presenter.employeelist

import com.employeemanagement.interactor.employeelist.EmployeeListInteractor
import com.employeemanagement.interactor.employeelist.EmployeeListInteractorImpl
import com.employeemanagement.interfaces.EmployeeListCallback
import com.employeemanagement.model.employeedelete.EmployeeDeleteResponse
import com.employeemanagement.model.employeelist.Data

class EmployeeListPresenterImpl(
    val employeeListCallback: EmployeeListCallback,
    val employeeListInteractorImpl: EmployeeListInteractorImpl
) : EmployeeListPresenter, EmployeeListInteractor.OnFinishedListener {


    override fun onSuccess(data: List<Data>) {
        employeeListCallback.onSuccess(data)
    }

    override fun onFailure(message: String) {
        employeeListCallback.onFail(message)
    }

    override fun getAllEmployee(data: Map<String, String>) {
        employeeListInteractorImpl.getEmployee(data, this)
    }

    override fun deleteEmployee(id: String) {
        employeeListInteractorImpl.deleteEmployee(id, this)
    }

    override fun onDeleteSuccess(data: EmployeeDeleteResponse) {
        employeeListCallback.onDeleteSuccess(data)
    }

    override fun onDeleteFail(message: String) {
        employeeListCallback.onFail(message)
    }
}