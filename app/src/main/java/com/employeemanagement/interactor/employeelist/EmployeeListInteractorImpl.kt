package com.employeemanagement.interactor.employeelist

import android.util.Log
import com.employeemanagement.model.employeedelete.EmployeeDeleteResponse
import com.employeemanagement.model.employeelist.EmployeeListResponse
import com.employeemanagement.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeListInteractorImpl : EmployeeListInteractor {
    override fun getEmployee(
        data: Map<String, String>,
        listener: EmployeeListInteractor.OnFinishedListener
    ) {
        val service = RetrofitInstance().getAPIService()
        val call = service.getEmployee(data)
        try {
            call.enqueue(object : Callback<EmployeeListResponse> {
                override fun onResponse(
                    call: Call<EmployeeListResponse>,
                    response: Response<EmployeeListResponse>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body!!.status == "success") {
                            listener.onSuccess(body!!.data)
                        } else {
                            listener.onFailure(body.status)
                        }

                    }
                }

                override fun onFailure(call: Call<EmployeeListResponse>, t: Throwable) {
                    listener.onFailure("Something went wrong")
                }
            })
        } catch (ex: Throwable) {
            Log.d("", "" + ex)
        }
    }

    override fun deleteEmployee(id: String, listener: EmployeeListInteractor.OnFinishedListener) {
        val service = RetrofitInstance().getAPIService()
        val call = service.deleteEmployee(id)
        try {
            call.enqueue(object : Callback<EmployeeDeleteResponse> {
                override fun onResponse(
                    call: Call<EmployeeDeleteResponse>,
                    response: Response<EmployeeDeleteResponse>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body!!.status == "success") {
                            listener.onDeleteSuccess(body!!)
                        } else {
                            listener.onDeleteFail(body.message)
                        }
                    }
                }

                override fun onFailure(call: Call<EmployeeDeleteResponse>, t: Throwable) {
                    listener.onFailure("Something went wrong")
                }
            })
        } catch (ex: Throwable) {
            Log.d("", "" + ex)
        }
    }
}