package rqg.fantasy.open163.tv.di.component

import dagger.Component
import dagger.android.AndroidInjectionModule
import rqg.fantasy.open163.tv.App
import rqg.fantasy.open163.tv.di.module.AppModule
import rqg.fantasy.open163.tv.di.module.BuildersModule
import rqg.fantasy.open163.tv.di.module.NetworkModule
import rqg.fantasy.open163.tv.di.module.Open163Module
import javax.inject.Singleton

/**
 * Created by rqg on 02/07/2017.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class,
        AndroidInjectionModule::class,
        Open163Module::class,
        NetworkModule::class,
        BuildersModule::class))
interface AppComponent {
    fun inject(app: App)
}