package jp.linhnk.dictionary.util

import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log

import org.apache.commons.lang3.StringUtils

import jp.linhnk.dictionary.R

/**
 * Dialog Helper
 */


class DialogUtils(context: Context) {

    private var progressDialog: ProgressDialog? = null
    private var message: String? = null

    init {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(context)
            message = context.getString(R.string.message_loading)
        }
    }

    @Synchronized fun show() {
        progressDialog!!.setMessage(message)
        progressDialog!!.setCancelable(false)
        progressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog!!.show()
    }

    @Synchronized fun dismiss() {
        if (progressDialog == null) {
            return
        }

        progressDialog!!.dismiss()
    }
}
