package tinny.com.runtimepermissions.permissions

interface PermissionCallBack{
    fun onAccpted(list:Array<String>)
    fun onDeined(list:Array<String>)
}