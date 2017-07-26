package jp.linhnk.dictionary.di.component


import dagger.Subcomponent
import jp.linhnk.dictionary.view.MainActivity
import jp.linhnk.dictionary.di.module.ActivityModule

/**
 * DI management interface class
 * 使いまわしたいActivityをinjectしていく
 */

@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(activity: MainActivity)
}
