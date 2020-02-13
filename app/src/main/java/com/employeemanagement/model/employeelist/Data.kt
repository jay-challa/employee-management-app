package com.employeemanagement.model.employeelist

import java.io.Serializable

data class Data(
    val employee_age: String,
    val employee_name: String,
    val employee_salary: String,
    val id: String,
    val profile_image: String
) : Serializable