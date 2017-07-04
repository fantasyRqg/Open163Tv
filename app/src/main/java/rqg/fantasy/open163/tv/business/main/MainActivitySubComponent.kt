package rqg.fantasy.open163.tv.business.main

import dagger.Subcomponent
import dagger.android.AndroidInjector
import rqg.fantasy.open163.tv.ActivityScope

/**
 * Created by rqg on 03/07/2017.
 */

@ActivityScope
@Subcomponent(modules = arrayOf(MainActivityModule::class))
interface MainActivitySubComponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}