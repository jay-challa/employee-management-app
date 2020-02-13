package com.employeemanagement.presenter.employeecreate

import com.employeemanagement.interactor.employeecreate.EmployeeCreateIInteractor
import com.employeemanagement.interactor.employeecreate.EmployeeCreateIInteractorImpl
import com.employeemanagement.interfaces.EmployeeCreateCallback
import com.employeemanagement.model.employeecreate.EmployeeCreateResponse
import com.employeemanagement.model.employeeupdate.EmployeeUpdateResponse

class EmployeeCreatePresenterImpl(
    val employeeCreateCallback: EmployeeCreateCallback,
    val employeeCreateIInteractorImpl: EmployeeCreateIInteractorImpl
) : EmployeeCreatePresenter, EmployeeCreateIInteractor.OnFinishedListener {

    override fun onSuccess(status: EmployeeCreateResponse) {
        employeeCreateCallback.onSucsess(status)
    }

    override fun onFailure(message: String) {
        employeeCreateCallback.onFail(message)
    }

    override fun saveEmployee(data: Map<String, String>) {
        employeeCreateIInteractorImpl.saveEmployee(data, this)
    }

    override fun updateEmployee(id: String, data: Map<String, String>) {
        employeeCreateIInteractorImpl.updateEmployee(id, data, this)
    }

    override fun onUpdateFailure(message: String) {
        employeeCreateCallback.onUpdateFail(message)
    }

    override fun onUpdateSuccess(data: EmployeeUpdateResponse) {
        employeeCreateCallback.onUpdateSuccess(data)
    }

}