package tinny.com.runtimepermissions

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import tinny.com.runtimepermissions.permissions.CheckPermissions
import tinny.com.runtimepermissions.permissions.PermissionCallBack

class MainActivity : AppCompatActivity(), PermissionCallBack {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CheckPermissions.askPermissions(
            this,
            arrayOf(
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_SMS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ), this
        )
    }

    override fun onAccepted(list: ArrayList<String>) {
        for (s in list) {
            Log.d("Test===onAccepted ", s)
        }
    }

    override fun onDenied(list: ArrayList<String>) {
        for (s in list) {
            Log.d("Test===onDenied ", s)
        }
    }

    override fun onNeverAskPermissions(list: ArrayList<String>) {
        for (s in list) {
            Log.d("Test===onNeverAsk", s)
        }
    }
}
