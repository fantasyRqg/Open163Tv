package rqg.fantasy.open163.tv

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by rqg on 02/07/2017.
 */


interface BasePresenter {
    fun start()
}

interface BaseView {
    fun getCompositeDisposable(): CompositeDisposable
}
