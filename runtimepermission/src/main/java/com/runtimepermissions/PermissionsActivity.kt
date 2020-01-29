package com.runtimepermissions

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import kotlinx.android.synthetic.main.fragment_permissions_request_dailog.*
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
        setFinishOnTouchOutside(false)
        window.statusBarColor = 0
        _mPermissions = intent.getStringArrayExtra(PermissionsManager.PERMISSIONS)
        permissionCallBack = PermissionsManager.callBack

        for (permission in _mPermissions) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                _mDeniedPermissions.add(permission)
            } else {
                _mAcceptedPermissions.add(permission)
            }
        }
        if (_mDeniedPermissions.size != 0) {
            requestPermissions(_mDeniedPermissions.toTypedArray(), PERMISSON_CODE)
        } else {
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
    }

    private fun checkNeverAskPermission(permission: String): Boolean {
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
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
