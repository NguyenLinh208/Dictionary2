package jp.linhnk.dictionary.di.module

import android.app.Application
import android.content.Context

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import io.realm.Realm

/**
 * Provide App Module
 */

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun provideApplicationContext(): Context {
        return application.applicationContext
    }


}
