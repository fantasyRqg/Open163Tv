package rqg.fantasy.open163.tv.di.component

import dagger.Component
import dagger.android.AndroidInjectionModule
import rqg.fantasy.open163.tv.App
import rqg.fantasy.open163.tv.di.module.AppModule
import rqg.fantasy.open163.tv.di.module.BuildersModule
import javax.inject.Singleton

/**
 * Created by rqg on 02/07/2017.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, AndroidInjectionModule::class, BuildersModule::class))
interface AppComponent {
    fun inject(app: App)
}