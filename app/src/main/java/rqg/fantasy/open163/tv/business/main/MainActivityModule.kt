package rqg.fantasy.open163.tv.business.main

import dagger.Binds
import dagger.Module

/**
 * * Created by rqg on 04/07/2017.
 */


@Module
abstract class MainActivityModule {
    @Binds
    abstract fun bindView(mainActivity: MainActivity): MainContract.View

    @Binds
    abstract fun bindPresenter(mainPresenterImpl: MainPresenterImpl): MainContract.Presenter
}