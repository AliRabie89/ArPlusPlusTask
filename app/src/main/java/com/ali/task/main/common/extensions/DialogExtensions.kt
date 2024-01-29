/*
 * Copyright (c) 2024. Eng Ali Rabie +201005886912
 */

package com.ali.task.main.common.extensions

import android.app.Dialog
import android.view.WindowManager

fun Dialog.setUpSheetUi() {
    window!!.setLayout(
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.WRAP_CONTENT
    )
    window!!.setBackgroundDrawableResource(android.R.color.transparent)
}

fun Dialog.dismissLoadingDialog() {
    if (this.isShowing) {
        this.dismiss()
    }
}