package rqg.fantasy.open163.tv

import android.app.Activity
import android.os.Bundle
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by rqg on 02/07/2017.
 */


abstract class BaseActivity : Activity() {
    protected lateinit var mCompositeDisposable: CompositeDisposable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCompositeDisposable = CompositeDisposable()
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.dispose()
    }


    fun getCompositeDisposable() = mCompositeDisposable

}