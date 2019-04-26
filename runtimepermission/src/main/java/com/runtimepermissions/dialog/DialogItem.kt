
package com.runtimepermissions.dialog

import android.graphics.drawable.Drawable

data class DialogItem(
  val icon: Drawable? = null,
  val title: String = "",
  val require: String = "",
  val description: String = ""
)
