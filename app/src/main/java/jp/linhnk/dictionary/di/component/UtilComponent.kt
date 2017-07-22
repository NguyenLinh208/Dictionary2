package jp.linhnk.dictionary.di.component

import dagger.Subcomponent
import jp.linhnk.dictionary.RealmController
import jp.linhnk.dictionary.di.module.UtilModule

@Subcomponent(modules = arrayOf(UtilModule::class))
interface UtilComponent {
    fun inject(realmController: RealmController)
}
