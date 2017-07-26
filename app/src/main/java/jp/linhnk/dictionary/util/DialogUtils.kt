package jp.linhnk.dictionary.util

import android.app.ProgressDialog
import android.content.Context

import jp.linhnk.dictionary.R
import rx.Observable

/**
 * Dialog Utils
 */

class DialogUtils {

    companion object {
        fun usingProgressDialog(context: Context): Observable<Void> {
            return Observable.using(
                    {
                        var progressDialog = ProgressDialog(context, ProgressDialog.STYLE_SPINNER)
                        progressDialog.setMessage(context.getString(R.string.message_loading))
                        progressDialog.show()
                        progressDialog
                    },
                    { Observable.just<Void>(null) },
                    { progressDialog -> progressDialog.dismiss() })
        }
    }


}
