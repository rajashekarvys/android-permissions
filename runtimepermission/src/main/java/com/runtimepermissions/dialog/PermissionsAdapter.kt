package com.runtimepermissions.dialog

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.view.LayoutInflater
import com.runtimepermissions.R
import kotlinx.android.synthetic.main.row.view.*


class PermissionsAdapter(var contextNew: Context, var listItems: ArrayList<DialogItem>) :
    ArrayAdapter<DialogItem>(contextNew, 0, listItems) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(contextNew).inflate(R.layout.row, parent, false)
        }


        view!!.permissionIcon.setImageDrawable(listItems[position].icon)
        view!!.permisssionText.text = listItems[position].title
        view!!.permissionDetails.text = listItems[position].description
        view!!.txtRequired.text = "(" + listItems[position].require + ")"

        return view!!
    }


}
