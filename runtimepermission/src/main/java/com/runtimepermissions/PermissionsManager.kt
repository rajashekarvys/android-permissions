package com.runtimepermissions

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build

object PermissionsManager {

    const val PERMISSIONS = "pr"
    const val CALLBACK = "cb"



    fun askPermissions(context: Context, permission: String, callBack: PermissionCallBack) {
        validatePermissions(context, arrayOf(permission), callBack)
    }


    fun askPermissions(context: Context, permissions: Array<String>, callBack: PermissionCallBack) {
        validatePermissions(context, permissions, callBack)
    }

    private fun validatePermissions(context: Context, permissions: Array<String>, callBack: PermissionCallBack) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            callBack.onAccepted(permissions.toCollection(ArrayList()))
        } else {
            for (permission in permissions) {
                if (context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    startPermissionActivity(context, permissions, callBack)
                    break
                }
            }
        }
    }

    private fun startPermissionActivity(context: Context, permissions: Array<String>, callBack: PermissionCallBack) {
        val intent = Intent(context, PermissionsActivity::class.java)
        intent.putExtra(PERMISSIONS, permissions)
        intent.putExtra(CALLBACK, callBack)
        context.startActivity(intent)
    }


}