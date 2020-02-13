package com.employeemanagement.interfaces

interface OnItemClick {
    fun onItemClick(pos : Int,any :Any)
    fun onLongClick(pos : Int,any :Any)

    fun onItemClickOff(pos : Int,any :Any)
    fun onLongClickOff(pos : Int,any :Any)
}