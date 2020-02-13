package com.employeemanagement.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.employeemanagement.R
import com.employeemanagement.adapter.EmployeeListAdapter
import com.employeemanagement.adapter.EmployeeListAdapterOff
import com.employeemanagement.databinding.ActivityEmployeeListBinding
import com.employeemanagement.interactor.employeelist.EmployeeListInteractorImpl
import com.employeemanagement.interfaces.EmployeeListCallback
import com.employeemanagement.interfaces.OnItemClick
import com.employeemanagement.model.employeedelete.EmployeeDeleteResponse
import com.employeemanagement.model.employeelist.Data
import com.employeemanagement.presenter.employeelist.EmployeeListPresenter
import com.employeemanagement.presenter.employeelist.EmployeeListPresenterImpl
import com.employeemanagement.room.entity.Employee
import com.employeemanagement.utils.Constant.checkInternet
import com.employeemanagement.utils.Constant.employeeRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.home_topbar.view.*
import kotlinx.android.synthetic.main.topbar.view.*

class EmployeeListActivity : AppCompatActivity(), EmployeeListCallback, OnItemClick,
    View.OnClickListener {

    lateinit var binding: ActivityEmployeeListBinding
    lateinit var employeeListPresenter: EmployeeListPresenter

    private var adapter: EmployeeListAdapter? = null
    private var adapterOff: EmployeeListAdapterOff? = null
    private var visibleItemCount = 0
    private var pastVisibleItemCount = 0
    private var totalItem: Int = 0
    private var previoustotal: Int = 0
    private var view_threshold: Int = 10
    private var loading: Boolean = false
    private var pageId: Int = 1
    private lateinit var layout_manager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this@EmployeeListActivity,
            R.layout.activity_employee_list
        )
        binding.topInclude.home_title_tv.text = "Employee List"
        binding.addButton.setOnClickListener(this@EmployeeListActivity)
        employeeListPresenter =
            EmployeeListPresenterImpl(this@EmployeeListActivity, EmployeeListInteractorImpl())

        layout_manager = LinearLayoutManager(this@EmployeeListActivity)
        binding.recyclerView.layoutManager = layout_manager

//        binding!!.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                visibleItemCount = layout_manager.childCount
//                totalItem = layout_manager.itemCount
//                pastVisibleItemCount =
//                    (binding!!.recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
//                if (dy > 0) {
//                    if (loading) {
//                        if (totalItem > previoustotal) {
//                            loading = false
//                            previoustotal = totalItem
//                        }
//                    }
//
//                    if (!loading && (totalItem - visibleItemCount) <= (pastVisibleItemCount + view_threshold)) {
//                        pageId++
//                        performPagination()
//                        loading = true
//                    }
//                }
//            }
//        })

    }

    override fun onResume() {
        super.onResume()
        checkNetwork()
    }

    fun performPagination() {
        var data = HashMap<String, String>()
        data["page"] = pageId.toString()
        employeeListPresenter.getAllEmployee(data)
    }


    fun checkNetwork() {
        if (checkInternet(this@EmployeeListActivity)) {
            var data = HashMap<String, String>()
            data["page"] = "1"
            employeeListPresenter.getAllEmployee(data)
        } else {
            var data = employeeRepository.getEmployeeList()
            if (!data.isNullOrEmpty()) {
                adapterOff = EmployeeListAdapterOff(
                    this@EmployeeListActivity,
                    data,
                    this@EmployeeListActivity
                )
                binding.recyclerView.adapter = adapterOff
                adapterOff!!.notifyDataSetChanged()

                binding.recyclerView.visibility = View.VISIBLE
                binding.noRecorcTv.visibility = View.GONE

            } else {
                binding.recyclerView.visibility = View.GONE
                binding.noRecorcTv.visibility = View.VISIBLE
            }
        }
    }

    override fun onSuccess(data: List<Data>) {
        if (!data.isNullOrEmpty()) {
            adapter =
                EmployeeListAdapter(this@EmployeeListActivity, data, this@EmployeeListActivity)
            binding.recyclerView.adapter = adapter
            adapter!!.notifyDataSetChanged()

            binding.recyclerView.visibility = View.VISIBLE
            binding.noRecorcTv.visibility = View.GONE

        } else {
            binding.recyclerView.visibility = View.GONE
            binding.noRecorcTv.visibility = View.VISIBLE
        }
    }

    override fun onFail(message: String) {
        Toast.makeText(this@EmployeeListActivity, message, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(pos: Int, any: Any) {
        var data = any as Data
        var intent = Intent(this@EmployeeListActivity, EmployeeDetailActivity::class.java)
        intent.putExtra("id", data.id)
        intent.putExtra("data", data)
        startActivity(intent)
    }

    override fun onLongClick(pos: Int, any: Any) {
        var data = any as Data
        showDialog(data.id, null, data)
    }

    override fun onItemClickOff(pos: Int, any: Any) {
        var employee = any as Employee
        var data = Data(
            employee.employee_age.toString(),
            employee.employee_name.toString(),
            employee.employee_salary.toString(),
            employee.id.toString(),
            employee.profile_image.toString()
        )
        var intent = Intent(this@EmployeeListActivity, EmployeeDetailActivity::class.java)
        intent.putExtra("id", data.id)
        intent.putExtra("data", data)
        startActivity(intent)
    }

    override fun onLongClickOff(pos: Int, any: Any) {
        var data = any as Employee
        showDialog(data.id.toString(), data, null)
    }

    private fun showDialog(id: String, employee: Employee?, da: Data?) {

        val array = arrayOf("Edit", "Delete")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose a option.")
        builder.setItems(array) { _, which ->
            val selected = array[which]
            try {
                when (selected) {
                    "Edit" -> {
                        var data: Data
                        if (employee != null) {
                            data = Data(
                                employee!!.employee_age.toString(),
                                employee!!.employee_name.toString(),
                                employee!!.employee_salary.toString(),
                                employee!!.id.toString(),
                                employee!!.profile_image.toString()
                            )
                        } else {
                            data = da!!
                        }

                        var intent =
                            Intent(this@EmployeeListActivity, EmployeeCreateActivity::class.java)
                        intent.putExtra("came_from", "edit")
                        intent.putExtra("id", id)
                        intent.putExtra("data", data)
                        startActivity(intent)
                    }
                    "Delete" -> {
                        if (checkInternet(this@EmployeeListActivity)) {
                            employeeListPresenter.deleteEmployee(id)
                        } else {
                            employeeRepository.deleteEmployee(employee)

                            checkNetwork()
                        }
                    }
                }
            } catch (e: IllegalArgumentException) {
            }
        }

        val dialog = builder.create()
        dialog.show()
    }

    override fun onDeleteFail(message: String) {
        showValidationMessage(message)
    }

    override fun onDeleteSuccess(data: EmployeeDeleteResponse) {
        showValidationMessage(data.message)
        checkNetwork()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.add_button -> {
                var intent = Intent(this@EmployeeListActivity, EmployeeCreateActivity::class.java)
                intent.putExtra("came_from", "add")
                intent.putExtra("id", "")
                startActivity(intent)
            }
        }
    }

    fun showValidationMessage(message: String) {
        Snackbar.make(binding!!.root, "" + message, Snackbar.LENGTH_LONG)
            .setAction("", null)
            .show()
    }

}
