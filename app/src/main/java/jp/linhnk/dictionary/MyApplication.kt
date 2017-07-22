package jp.linhnk.dictionary

import android.app.Application

import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by BUBUSU on 2017/07/17.
 */

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}
