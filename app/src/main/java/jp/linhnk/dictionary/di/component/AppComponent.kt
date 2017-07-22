package jp.linhnk.dictionary.di.component

import javax.inject.Singleton

import dagger.Component
import jp.linhnk.dictionary.di.module.AppModule

/**
 * Created by BUBUSU on 2017/07/17.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun activityComponent(): ActivityComponent
}
