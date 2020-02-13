package com.employeemanagement.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.employeemanagement.R
import com.employeemanagement.databinding.ActivityEmployeeDetailBinding
import com.employeemanagement.model.employeelist.Data
import kotlinx.android.synthetic.main.topbar.view.*

class EmployeeDetailActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var binding: ActivityEmployeeDetailBinding
    lateinit var data: Data
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this@EmployeeDetailActivity,
            R.layout.activity_employee_detail
        )

        binding.topInclude.title_tv.text = "Employee Details"
        binding.topInclude.back_rl.setOnClickListener(this@EmployeeDetailActivity)

        data = intent!!.extras?.get("data") as Data

        binding.nameTv.setText(data.employee_name)
        binding.ageTv.setText(data.employee_age)
        binding.salaryTv.setText(data.employee_salary)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.back_rl -> {
                onBackPressed()
            }
        }
    }
}
