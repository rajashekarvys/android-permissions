package com.runtimepermissions.dialog


import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import com.runtimepermissions.R
import com.skydoves.needs.TextForm
import kotlinx.android.synthetic.main.fragment_permissions_request_dailog.view.*


class PermissionsRequestDialog(activity: AppCompatActivity, var builder: Builder) {


    private var view: View
    private var builderPro: Builder

    init {
        builderPro = builder
        view = activity.layoutInflater.inflate(R.layout.fragment_permissions_request_dailog, null).apply {
            appIcon.setImageDrawable(builderPro.appIcon)
            titleDescription.text = builderPro.titleDescription

            permissionList.adapter = PermissionsAdapter(contextNew = activity, listItems = builderPro.itemsList)

        }
        val width = (activity.resources.displayMetrics.widthPixels * 0.95).toInt()
        var height :Int
        if (builderPro.itemsList.size < 3)
            height = (activity.resources.displayMetrics.heightPixels * 0.60).toInt()
        else
            height = (activity.resources.displayMetrics.heightPixels * 0.80).toInt()

        AlertDialog.Builder(activity)
            .create().apply {
                setView(view)
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setCanceledOnTouchOutside(true)
                show()
                window.setLayout(width, height)

            }
    }

    fun setOnConfirmListener(onConfirmListener: OnConfirmListener) {
        view.btn.setOnClickListener { onConfirmListener.onConfirm() }
    }


    class Builder(private val context: AppCompatActivity) {

        var appIcon: Drawable? = null
        var titleDescription: String = "Title"
        var titleDescriptionForm: TextForm? = null

        var suggestion: String = "description"
        var suggestionTextForm: TextForm? = null

        var confirm: String = "Confirm"
        var confirmTextForm: TextForm? = null

        val itemsList = ArrayList<DialogItem>()
        var background: Drawable? = null
        var backgroundColor: Int = Color.BLACK

        fun setAppIcon(@DrawableRes drawable: Drawable): Builder = apply { this.appIcon = drawable }
        fun setTitleDescription(value: String): Builder = apply { this.titleDescription = value }
        fun setTitleDescriptionForm(value: TextForm): Builder = apply { this.titleDescriptionForm = value }
        fun setSuggestion(value: String): Builder = apply { this.suggestion = value }
        fun setSuggestionTextForm(value: TextForm): Builder = apply { this.suggestionTextForm = value }
        fun setConfirm(value: String): Builder = apply { this.confirm = value }
        fun setConfirmTextForm(value: TextForm): Builder = apply { this.confirmTextForm = value }
        fun addNeedsItemList(value: List<DialogItem>): Builder = apply { this.itemsList.addAll(value) }
        fun setBackground(@DrawableRes value: Drawable): Builder = apply { this.background = value }
        fun setBackgroundColor(@ColorInt value: Int): Builder = apply { this.backgroundColor = value }
        fun build(): PermissionsRequestDialog {
            return PermissionsRequestDialog(context, this)
        }

    }

}
