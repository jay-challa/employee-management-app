package com.employeemanagement.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.employeemanagement.R
import com.employeemanagement.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {


    lateinit var binding : ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@SplashScreenActivity,R.layout.activity_splash_screen)


        Handler().postDelayed({
            val start = Intent(this@SplashScreenActivity, EmployeeListActivity::class.java)
            startActivity(start)
            finish()
        }, 2000)
    }
}
