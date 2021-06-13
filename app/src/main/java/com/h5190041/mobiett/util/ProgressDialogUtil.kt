package com.h5190041.mobiett.util

import android.app.Activity
import com.h5190041.mobiett.R
import androidx.appcompat.app.AlertDialog


class ProgressDialogUtil(val mActivity: Activity) {
    private lateinit var isdialog:AlertDialog
    fun progressDialogBasla(){
        val infalter = mActivity.layoutInflater
        val dialogView = infalter.inflate(R.layout.progres_dialog,null)
        val bulider = AlertDialog.Builder(mActivity)

        bulider.setView(dialogView)
        bulider.setCancelable(false)

        isdialog = bulider.create()
        isdialog.show()

        isdialog.create()
        isdialog.show()
    }
    fun progressDialogBitir() {

            isdialog.dismiss()

    }
}