package rqg.fantasy.open163.tv.di.module

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import rqg.fantasy.open163.tv.business.main.MainActivity
import rqg.fantasy.open163.tv.business.main.MainContract
import rqg.fantasy.open163.tv.business.main.MainPresenterImpl
import rqg.fantasy.open163.tv.di.component.MainActivitySubComponent


/**
 * Created by rqg on 03/07/2017.
 */

@Module(subcomponents = arrayOf(MainActivitySubComponent::class))
abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    internal abstract fun bindMainActivityInjectorFactory(builder: MainActivitySubComponent.Builder): AndroidInjector.Factory<out Activity>


    @Binds
    abstract fun bindMainPresenter(mainPresenterImpl: MainPresenterImpl): MainContract.Presenter

    @Binds
    abstract fun bindMainView(mainActivity: MainActivity): MainContract.View

}