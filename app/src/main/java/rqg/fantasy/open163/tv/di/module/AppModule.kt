package rqg.fantasy.open163.tv.di.module

import dagger.Module
import dagger.Provides
import rqg.fantasy.open163.tv.App
import javax.inject.Singleton

/**
 * Created by rqg on 02/07/2017.
 */

@Module
class AppModule(val app: App) {
    @Provides
    @Singleton
    fun provideMyApplication() = app
}