package com.employeemanagement.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.employeemanagement.room.database.EmployeeDatabase
import com.employeemanagement.room.repository.EmployeeRepository

object Constant {
    lateinit var employeeDatabase: EmployeeDatabase
    lateinit var employeeRepository: EmployeeRepository

    fun checkInternet(context: Context): Boolean {
        var cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var networkInfo = cm.activeNetworkInfo;
        if (networkInfo != null && networkInfo.isConnected) {
            return true;
        }
        try {
            Toast.makeText(context, "No network available", Toast.LENGTH_LONG).show();
        } catch (e: Exception) {
            e.printStackTrace();
        }
        return false;

    }

}