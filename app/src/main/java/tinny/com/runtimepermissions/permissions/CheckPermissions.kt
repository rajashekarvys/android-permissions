package tinny.com.runtimepermissions.permissions

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build

object CheckPermissions {

    const val PERMISSIONS = "pr"
    const val CALLBACK = "cb"

    internal

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
            startPermissionActivity(context, permissions, callBack)
        }
    }

    private fun startPermissionActivity(context: Context, permissions: Array<String>, callBack: PermissionCallBack) {
        val intent = Intent(context, PermissionsActivity::class.java)
        intent.putExtra(PERMISSIONS, permissions)
        intent.putExtra(CALLBACK, callBack)
        context.startActivity(intent)
    }


}