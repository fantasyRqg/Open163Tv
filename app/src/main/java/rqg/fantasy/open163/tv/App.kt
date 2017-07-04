package rqg.fantasy.open163.tv

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import rqg.fantasy.open163.tv.di.component.AppComponent
import rqg.fantasy.open163.tv.di.component.DaggerAppComponent
import rqg.fantasy.open163.tv.di.module.AppModule
import javax.inject.Inject


/**
 * * Created by rqg on 02/07/2017.
 */

class App : Application(), HasActivityInjector {
    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return dispatchingActivityInjector
    }

    @Inject lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>


    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }


    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}