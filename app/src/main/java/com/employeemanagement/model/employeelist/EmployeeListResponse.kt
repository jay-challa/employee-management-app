package com.employeemanagement.model.employeelist

data class EmployeeListResponse(
    val `data`: List<Data>,
    val status: String
)