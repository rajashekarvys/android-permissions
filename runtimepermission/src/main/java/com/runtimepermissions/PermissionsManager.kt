package com.runtimepermissions

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build

object PermissionsManager {

    const val PERMISSIONS = "pr"
    const val CALLBACK = "cb"
    lateinit var callBack: PermissionCallBack
    var allPermissionGranted = false

    fun askPermissions(context: Context, permission: String, callBack: PermissionCallBack) {
        validatePermissions(context, arrayOf(permission), callBack)
    }


    fun askPermissions(context: Context, permissions: Array<String>, callBack: PermissionCallBack) {
        validatePermissions(context, permissions, callBack)
    }

    fun askPermissions(context: Context, permissions: ArrayList<String>, callBack: PermissionCallBack) {
        validatePermissions(context, permissions.toArray() as Array<String>, callBack)
    }

    private fun validatePermissions(context: Context, permissions: Array<String>, callBack: PermissionCallBack) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            callBack.onAccepted(permissions.toCollection(ArrayList()))
        } else {
            for (permission in permissions) {
                allPermissionGranted =true
                if (context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    allPermissionGranted =false
                    this.callBack = callBack
                    startPermissionActivity(context, permissions)
                    break
                }
            }
            if (allPermissionGranted){
                callBack.onAccepted(permissions.toCollection(ArrayList()))
            }
        }
    }

    private fun startPermissionActivity(context: Context, permissions: Array<String>) {
        val intent = Intent(context, PermissionsActivity::class.java)
        intent.putExtra(PERMISSIONS, permissions)
        context.startActivity(intent)
    }


}