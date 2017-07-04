package rqg.fantasy.open163.tv.di.module

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import rqg.fantasy.open163.tv.business.main.MainActivity
import rqg.fantasy.open163.tv.business.main.MainActivitySubComponent


/**
 * Created by rqg on 03/07/2017.
 */

@Module(subcomponents = arrayOf(MainActivitySubComponent::class))
abstract class BuildersModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun bindMainActivityInjectorFactory(builder: MainActivitySubComponent.Builder): AndroidInjector.Factory<out Activity>


}