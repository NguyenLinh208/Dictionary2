package jp.linhnk.dictionary

import android.app.Activity
import android.app.Fragment
import android.content.Context
import io.realm.Realm
import io.realm.RealmObject
import jp.linhnk.dictionary.datamodel.Word

import javax.inject.Singleton

/**
 * Created by BUBUSU on 2017/07/17.
 */

@Singleton
class RealmController(context: Context) {
    val realm: Realm

    init {
        realm = Realm.getDefaultInstance()
    }

    fun refresh() {
        realm.refresh()
    }

    fun insertData(word : RealmObject) {
        realm.copyToRealmOrUpdate(word)
    }


    fun clearAll() {
        realm.beginTransaction()
        realm.deleteAll()
        realm.commitTransaction()
    }

    companion object {
        private var instance : RealmController? = null

        fun getInstance(activity: Activity) : RealmController {
            if (instance == null) {
                instance = RealmController(activity.applicationContext)
            }

            return instance!!
        }

        fun getInstance(fragment: Fragment) : RealmController {
            if (instance == null) {
                instance = RealmController(fragment.activity.applicationContext)
            }

            return instance!!
        }
    }
}
