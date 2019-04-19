package tinny.com.runtimepermissions.permissions

import java.io.Serializable

interface PermissionCallBack : Serializable {
    fun onAccepted(list: ArrayList<String>)
    fun onDenied(list: ArrayList<String>)
    fun onNeverAskPermissions(list: ArrayList<String>)
}