package tinny.com.runtimepermissions.permissions

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import java.util.*

class PermissionsActivity : Activity() {


    private lateinit var _mPermissions: Array<String>
    private val _mAcceptedPermissions = ArrayList<String>()
    private val _mDeniedPermissions = ArrayList<String>()
    private val _mdNeverAskPermissions = ArrayList<String>()

    private val PERMISSON_CODE = 123
    private lateinit var permissionCallBack: PermissionCallBack

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _mPermissions = intent.getStringArrayExtra(CheckPermissions.PERMISSIONS)
        permissionCallBack = intent.getSerializableExtra(CheckPermissions.CALLBACK) as PermissionCallBack


        for (permission in _mPermissions) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                if (!checkNeverAskPermission(permission))
                    _mdNeverAskPermissions.add(permission)
                else
                    _mDeniedPermissions.add(permission)
            } else {
                _mAcceptedPermissions.add(permission)
            }
        }
        if (_mDeniedPermissions.size != 0) {
            requestPermissions(_mDeniedPermissions.toTypedArray(), PERMISSON_CODE)
        }else{
            permissionCallBack.onAccepted(_mAcceptedPermissions)
            permissionCallBack.onDenied(_mDeniedPermissions)
            permissionCallBack.onNeverAskPermissions(_mdNeverAskPermissions)
            finish()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        _mDeniedPermissions.clear()
        if (grantResults.isEmpty()) {
            for (permission in permissions) {
                _mDeniedPermissions.add(permission)
            }

        }

        for (grant in grantResults.indices) {
            if (grantResults[grant] == PackageManager.PERMISSION_GRANTED) {
                _mAcceptedPermissions.add(permissions[grant])
            } else {
                if (!checkNeverAskPermission(permissions[grant]))
                    _mdNeverAskPermissions.add(permissions[grant])
                else
                    _mDeniedPermissions.add(permissions[grant])
            }
        }
        permissionCallBack.onAccepted(_mAcceptedPermissions)
        permissionCallBack.onDenied(_mDeniedPermissions)
        permissionCallBack.onNeverAskPermissions(_mdNeverAskPermissions)
        finish()
/*
        val array = arrayOfNulls<String>(_mAcceptedPermissions.size)
        permissionCallBack.onAccepted(array)

        val array = arrayOfNulls<String>(_mAcceptedPermissions.size)
        permissionCallBack.onAccepted(array)
*/

    }

    private fun checkNeverAskPermission(permission: String): Boolean {
        return try {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                shouldShowRequestPermissionRationale(permission)
            } else {
                false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    }

}
