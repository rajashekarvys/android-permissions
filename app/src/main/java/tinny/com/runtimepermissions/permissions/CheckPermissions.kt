package tinny.com.runtimepermissions.permissions

import android.content.Context
import android.os.Build

object CheckPermissions {


    fun askPermissions(context: Context, permission: String, callBack: PermissionCallBack) {
        validatePermissions(context, arrayOf(permission),callBack)
    }

    fun askPermissions(context: Context, permissions: Array<String>, callBack: PermissionCallBack) {
        validatePermissions(context,permissions,callBack)
    }

    private fun validatePermissions(context: Context, perssions: Array<String>, callBack: PermissionCallBack){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            callBack.onAccpted(perssions)
        }else{

        }
    }

}