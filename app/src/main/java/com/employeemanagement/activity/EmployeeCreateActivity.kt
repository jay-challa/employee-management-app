package com.employeemanagement.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.databinding.DataBindingUtil
import com.employeemanagement.R
import com.employeemanagement.databinding.ActivityEmployeeCreateBinding
import com.employeemanagement.interactor.employeecreate.EmployeeCreateIInteractorImpl
import com.employeemanagement.interactor.employeelist.EmployeeListInteractorImpl
import com.employeemanagement.interfaces.EmployeeCreateCallback
import com.employeemanagement.model.employeecreate.EmployeeCreateResponse
import com.employeemanagement.model.employeelist.Data
import com.employeemanagement.model.employeeupdate.EmployeeUpdateResponse
import com.employeemanagement.presenter.employeecreate.EmployeeCreatePresenter
import com.employeemanagement.presenter.employeecreate.EmployeeCreatePresenterImpl
import com.employeemanagement.presenter.employeelist.EmployeeListPresenterImpl
import com.employeemanagement.room.entity.Employee
import com.employeemanagement.utils.Constant.checkInternet
import com.employeemanagement.utils.Constant.employeeRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.topbar.view.*

class EmployeeCreateActivity : AppCompatActivity(), View.OnClickListener, EmployeeCreateCallback {

    lateinit var data: Data
    lateinit var binding: ActivityEmployeeCreateBinding
    lateinit var employeeCreatePresenter: EmployeeCreatePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this@EmployeeCreateActivity,
            R.layout.activity_employee_create
        )
        if (intent.getStringExtra("came_from") == "add") {
            binding.topInclude.title_tv.text = "Create Employee"
        } else {
            binding.topInclude.title_tv.text = "Edit Employee"
            data = intent.extras?.get("data") as Data
            binding.nameEt.setText(data.employee_name)
            binding.ageEt.setText(data.employee_age)
            binding.salaryEt.setText(data.employee_salary)
        }

        binding.topInclude.back_rl.setOnClickListener(this@EmployeeCreateActivity)
        binding.saveBtn.setOnClickListener(this@EmployeeCreateActivity)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.save_btn -> {
                validationCheck()
            }
            R.id.back_rl -> {
                onBackPressed()
            }
        }
    }

    private fun validationCheck() {
        if (binding.nameEt.text.toString() == "") {
            showValidationMessage("Please enter name")
        } else if (binding.salaryEt.text.toString() == "") {
            showValidationMessage("Please enter salary")
        } else if (binding.ageEt.text.toString() == "") {
            showValidationMessage("Please enter age")
        } else {
            if (intent.getStringExtra("came_from") == "add")
                checkInternetAndSave()
            else {
                var id = intent.getStringExtra("id")
                checkInternetAndUpdate(id)
            }

        }
    }

    private fun checkInternetAndSave() {
        if (checkInternet(this@EmployeeCreateActivity)) {
            employeeCreatePresenter =
                EmployeeCreatePresenterImpl(
                    this@EmployeeCreateActivity,
                    EmployeeCreateIInteractorImpl()
                )
            var map = HashMap<String, String>()
            map["name"] = binding.nameEt.text.toString()
            map["salary"] = binding.salaryEt.text.toString()
            map["age"] = binding.ageEt.text.toString()

            employeeCreatePresenter.saveEmployee(map)
        } else {
            var employee = Employee()
            employee.employee_name = binding.nameEt.text.toString()
            employee.employee_salary = binding.salaryEt.text.toString()
            employee.employee_age = binding.ageEt.text.toString()
            employeeRepository.saveEmployee(employee)

            binding.nameEt.setText("")
            binding.salaryEt.setText("")
            binding.ageEt.setText("")

            showValidationMessage(" Added successfully ")
            Handler().postDelayed({
                onBackPressed()
            }, 1000)
        }
    }

    private fun checkInternetAndUpdate(id: String) {
        if (checkInternet(this@EmployeeCreateActivity)) {
            employeeCreatePresenter =
                EmployeeCreatePresenterImpl(
                    this@EmployeeCreateActivity,
                    EmployeeCreateIInteractorImpl()
                )
            var map = HashMap<String, String>()
            map["name"] = binding.nameEt.text.toString()
            map["salary"] = binding.salaryEt.text.toString()
            map["age"] = binding.ageEt.text.toString()

            employeeCreatePresenter.updateEmployee(id, map)
        } else {
            employeeRepository.updateEmployee(
                id.toInt(),
                binding.nameEt.text.toString(),
                binding.ageEt.text.toString(),
                binding.salaryEt.text.toString()
            )

            binding.nameEt.setText("")
            binding.salaryEt.setText("")
            binding.ageEt.setText("")

            showValidationMessage("Updated successfully ")
            Handler().postDelayed({
                onBackPressed()
            }, 1000)
        }
    }

    override fun onUpdateFail(message: String) {
        showValidationMessage(message)
    }

    override fun onUpdateSuccess(data: EmployeeUpdateResponse) {
        showValidationMessage(data.status + " - Updated successfully ")
        Handler().postDelayed({
            onBackPressed()
        }, 1000)
    }

    override fun onSucsess(status: EmployeeCreateResponse) {
        showValidationMessage(status.status + " - Added successfully ")
        Handler().postDelayed({
            onBackPressed()
        }, 1000)
    }

    override fun onFail(message: String) {
        showValidationMessage(message)
    }

    fun showValidationMessage(message: String) {
        Snackbar.make(binding!!.root, "" + message, Snackbar.LENGTH_LONG)
            .setAction("", null)
            .show()
    }
}
