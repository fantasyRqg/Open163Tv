package rqg.fantasy.open163.tv.di.component

import dagger.Subcomponent
import dagger.android.AndroidInjector
import rqg.fantasy.open163.tv.business.main.MainActivity

/**
 * Created by rqg on 03/07/2017.
 */

@Subcomponent
interface MainActivitySubComponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}