package rqg.fantasy.open163.tv.di.component

import dagger.Component
import rqg.fantasy.open163.tv.App
import rqg.fantasy.open163.tv.di.module.AppModule
import rqg.fantasy.open163.tv.di.module.MainActivityModule
import javax.inject.Singleton

/**
 * Created by rqg on 02/07/2017.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, MainActivityModule::class))
interface AppComponent {
    fun getApp(): App
    fun inject(app: App)
}