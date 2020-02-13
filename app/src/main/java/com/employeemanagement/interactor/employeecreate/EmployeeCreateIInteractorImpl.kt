package com.employeemanagement.interactor.employeecreate

import android.util.Log
import com.employeemanagement.model.employeecreate.EmployeeCreateResponse
import com.employeemanagement.model.employeelist.EmployeeListResponse
import com.employeemanagement.model.employeeupdate.EmployeeUpdateResponse
import com.employeemanagement.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeCreateIInteractorImpl : EmployeeCreateIInteractor {
    override fun saveEmployee(
        data: Map<String, String>,
        listner: EmployeeCreateIInteractor.OnFinishedListener
    ) {
        val service = RetrofitInstance().getAPIService()
        val call = service.createEmployee(data)
        try {
            call.enqueue(object : Callback<EmployeeCreateResponse> {
                override fun onResponse(call: Call<EmployeeCreateResponse>, response: Response<EmployeeCreateResponse>) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body!!.status == "success") {
                            listner.onSuccess(body)
                        } else {
                            listner.onFailure(body.status)
                        }

                    }
                }

                override fun onFailure(call: Call<EmployeeCreateResponse>, t: Throwable) {
                    listner.onFailure("Something went wrong")
                }
            })
        } catch (ex: Throwable) {
            Log.d("", "" + ex)
        }
    }

    override fun updateEmployee(
        id: String,
        data: Map<String, String>,
        listner: EmployeeCreateIInteractor.OnFinishedListener
    ) {
        val service = RetrofitInstance().getAPIService()
        val call = service.updateEmployeeData(id,data)
        try {
            call.enqueue(object : Callback<EmployeeUpdateResponse> {
                override fun onResponse(call: Call<EmployeeUpdateResponse>, response: Response<EmployeeUpdateResponse>) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body!!.status == "success") {
                            listner.onUpdateSuccess(body)
                        } else {
                            listner.onUpdateFailure(body.status)
                        }

                    }
                }

                override fun onFailure(call: Call<EmployeeUpdateResponse>, t: Throwable) {
                    listner.onFailure("Something went wrong")
                }
            })
        } catch (ex: Throwable) {
            Log.d("", "" + ex)
        }
    }
}