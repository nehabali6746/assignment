package com.kotlin.assignmenttask.utils

import android.app.Activity
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.kotlin.assignmenttask.R
import com.wang.avi.AVLoadingIndicatorView


/**
 * Created by Sarthi on 21/10/16.
 */
class CustomProgressDialog {
    var mProgressDialog: ProgressDialog? = null
    var mActivity: Activity? = null
    private var avLoadingIndicatorView: AVLoadingIndicatorView? = null
    fun createDialog(activity: Activity?) {
        try {
            mActivity = activity
            mProgressDialog = ProgressDialog(activity)
            mProgressDialog!!.setCancelable(false)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showDialog() {
        if (mProgressDialog == null) return
        mProgressDialog!!.show()
        mProgressDialog?.setContentView(R.layout.dialog_progress)
        avLoadingIndicatorView = mProgressDialog!!.findViewById(R.id.avi)
        avLoadingIndicatorView?.show()
        mProgressDialog!!.window!!.setLayout(mActivity!!.window.windowManager.defaultDisplay.width, mActivity!!.window.windowManager.defaultDisplay.height)
        mProgressDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun dismissDialog() {
        if (mProgressDialog == null) return
        if (mProgressDialog!!.isShowing) mProgressDialog!!.dismiss()
        avLoadingIndicatorView?.hide()
    }

    fun setCancelable(isCancelable: Boolean) {
        mProgressDialog!!.setCancelable(isCancelable)
    }
}