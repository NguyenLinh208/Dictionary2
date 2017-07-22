package jp.linhnk.dictionary

import android.app.ProgressDialog
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView

import io.realm.Realm
import jp.linhnk.dictionary.util.CsvParser
import jp.linhnk.dictionary.util.DialogUtils

class MainActivity : AppCompatActivity() {

    private var mTextMessage: TextView? = null

    private var myRealm: Realm? = null

    var progressDialog: ProgressDialog? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                mTextMessage!!.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                mTextMessage!!.setText(R.string.title_history)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                mTextMessage!!.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTextMessage = findViewById(R.id.message) as TextView
        val navigation = findViewById(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
//        DialogUtils(this).show()
        initData()
    }

    private fun initData() {
        myRealm = RealmController.getInstance(this).realm
        RealmController.getInstance(this).clearAll()
        val wordList = CsvParser.parser(this, myRealm!!)
        Log.i("wordlist", wordList.size.toString())
        myRealm!!.beginTransaction()
        wordList.forEach { word ->
            RealmController.getInstance(this).insertData(word)
        }

        myRealm!!.commitTransaction()
//        DialogUtils(this).dismiss()
    }

}
