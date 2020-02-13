package com.employeemanagement.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.employeemanagement.R
import com.employeemanagement.databinding.RecyclerItemBinding
import com.employeemanagement.interfaces.OnItemClick
import com.employeemanagement.model.employeelist.Data
import com.employeemanagement.room.entity.Employee
import kotlinx.android.synthetic.main.recycler_item.view.*

class EmployeeListAdapterOff (var context: Context,
var list: List<Employee>,
var onItemClick: OnItemClick
) : RecyclerView.Adapter<EmployeeListAdapterOff.ViewHolder>() {
    lateinit var binding: RecyclerItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.recycler_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.name_tv.text = list[position].employee_name
        holder.itemView.salary_tv.text = "Salary - " + list[position].employee_salary
        holder.itemView.age_tv.text = "Age - " + list[position].employee_age

        holder.itemView.setOnClickListener {
            onItemClick.onItemClickOff(position, list[position])
        }

        holder.itemView.recycler_view_item.setOnLongClickListener(object : View.OnLongClickListener{
            override fun onLongClick(v: View?): Boolean {
                onItemClick.onLongClickOff(position, list[position])
                return true
            }
        })


    }

    class ViewHolder(binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}