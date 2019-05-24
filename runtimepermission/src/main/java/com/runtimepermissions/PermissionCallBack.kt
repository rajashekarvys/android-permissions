package com.runtimepermissions

import java.io.Serializable

interface PermissionCallBack : Serializable {
    fun onAccepted(list: ArrayList<String>)
    fun onDenied(list: ArrayList<String>)
    fun onNeverAskPermissions(list: ArrayList<String>)

}