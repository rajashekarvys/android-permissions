package tinny.com.runtimepermissions

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.runtimepermissions.PermissionsManager
import com.runtimepermissions.PermissionCallBack
import com.runtimepermissions.dialog.DialogItem
import com.runtimepermissions.dialog.OnConfirmListener
import com.runtimepermissions.dialog.PermissionsRequestDialog


class MainActivity : AppCompatActivity(), OnConfirmListener {

    override fun onConfirm() {
        PermissionsManager.askPermissions(
            this,
            arrayOf(
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_SMS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ), object : PermissionCallBack {
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
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val items = ArrayList<DialogItem>()

        items.add(DialogItem(resources.getDrawable(R.mipmap.ic_launcher),title = "SMS",description = "Need this permission for read SMS",require = "required"))
        items.add(DialogItem(resources.getDrawable(R.mipmap.ic_launcher),title = "Contacts",description = "Need this permission for read SMS",require = "required"))
        items.add(DialogItem(resources.getDrawable(R.mipmap.ic_launcher),title = "Location",description = "Need this permission for read SMS",require = "optional"))
        items.add(DialogItem(resources.getDrawable(R.mipmap.ic_launcher),title = "Phone",description = "Need this permission for read SMS",require = "required"))
        items.add(DialogItem(resources.getDrawable(R.mipmap.ic_launcher),title = "SMSNEW",description = "Need this permission for read SMS",require = "required"))

        val pd = PermissionsRequestDialog.Builder(this).apply {
            setAppIcon(resources.getDrawable(R.mipmap.ic_launcher))
            setTitleDescription("Following permissions are used in Demo App")
            addNeedsItemList(items)
        }
        pd.build().setOnConfirmListener(this)



    }

}
