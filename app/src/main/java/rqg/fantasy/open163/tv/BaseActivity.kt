package rqg.fantasy.open163.tv

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by rqg on 02/07/2017.
 */


abstract class BaseActivity : FragmentActivity() {
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