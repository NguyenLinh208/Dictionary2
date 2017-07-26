package jp.linhnk.dictionary.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import jp.linhnk.dictionary.R

import jp.linhnk.dictionary.util.CsvParser
import jp.linhnk.dictionary.util.DialogUtils
import rx.Observable
import android.os.SystemClock
import android.widget.TextView
import jp.linhnk.dictionary.databinding.ActivityMainBinding
import jp.linhnk.dictionary.datamodel.Word
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import android.app.ProgressDialog
import android.content.Context
import com.vicpin.krealmextensions.saveAll


class MainActivity : AppCompatActivity() {
    private var message: TextView? = null
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message!!.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message!!.setText(R.string.title_history)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message!!.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        message = binding.message
        binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        binding.showDialog.setOnClickListener {
            usingProgressDialog(this)
                    .flatMap({
                        loadData()
                    })
                    .subscribe({ list ->
                        list.saveAll()
                    })
        }
    }


    private fun loadData(): Observable<MutableList<Word>> {
        return Observable
                .create(Observable.OnSubscribe<MutableList<Word>> { subscriber ->
                    SystemClock.sleep(3000)
                    subscriber.onNext(CsvParser.parser(applicationContext))
                    subscriber.onCompleted()
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun usingProgressDialog(context: Context): Observable<Void> {
        return Observable.using(
                {
                    val progressDialog = createProgressDialog()
                    progressDialog.setMessage(context.getString(R.string.message_loading))
                    progressDialog.show()
                    progressDialog
                },
                { Observable.just<Void>(null) },
                { progressDialog -> progressDialog.dismiss() })
    }

    private fun createProgressDialog(): ProgressDialog {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        return progressDialog
    }
}
