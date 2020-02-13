package com.employeemanagement.retrofit

import com.employeemanagement.model.employeecreate.EmployeeCreateResponse
import com.employeemanagement.model.employeedelete.EmployeeDeleteResponse
import com.employeemanagement.model.employeelist.EmployeeListResponse
import com.employeemanagement.model.employeeupdate.EmployeeUpdateResponse
import com.employeemanagement.room.entity.Employee
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {

    @GET("employees")
    fun getEmployee(@QueryMap data: Map<String, String>) : Call<EmployeeListResponse>


    @GET("employee/{id}")
    fun getEmployeeById(@Path("id") id : String)

    @FormUrlEncoded
    @POST("create")
    fun createEmployee(@FieldMap data : Map<String,String>) : Call<EmployeeCreateResponse>

    @FormUrlEncoded
    @PUT("update/{id}")
    fun updateEmployeeData(@Path("id") id : String, @FieldMap data: Map<String,String>) : Call<EmployeeUpdateResponse>

    @DELETE("delete/{id}")
    fun deleteEmployee(@Path("id") id : String) : Call<EmployeeDeleteResponse>

}

