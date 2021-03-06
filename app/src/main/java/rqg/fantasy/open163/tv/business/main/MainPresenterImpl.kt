package rqg.fantasy.open163.tv.business.main

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import rqg.fantasy.open163.tv.ActivityScope
import rqg.fantasy.open163.tv.api.Open163Api
import rqg.fantasy.open163.tv.model.MovieItem
import javax.inject.Inject

/**
 * Created by rqg on 02/07/2017.
 */


@ActivityScope
class MainPresenterImpl @Inject constructor(val mView: MainContract.View, val open163Api: Open163Api) : MainContract.Presenter {


    val TAG = "MainPresenterImpl"

    private lateinit var mPlayTable: Map<String, MutableList<MovieItem>>

    override fun start() {
        open163Api.getPlays()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map {
                    val playTable: MutableMap<String, MutableList<MovieItem>> = mutableMapOf()

                    it.requireNoNulls()
                            .forEach {
                                var types = it.type?.split(",")

                                types?.requireNoNulls()
                                        ?.forEach { key ->
                                            if (playTable.containsKey(key)) {
                                                playTable[key]?.add(it)
                                            } else {
                                                val list: MutableList<MovieItem> = mutableListOf()
                                                list.add(it)
                                                playTable[key] = list
                                            }
                                        }
                            }

                    return@map playTable
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mPlayTable = it
                    val cnameList = mPlayTable.keys.toMutableList()
                    cnameList.add(0, String(byteArrayOf(0xF0.toByte(), 0x9F.toByte(), 0x94.toByte(), 0x8D.toByte())))
                    mView.showMenuList(cnameList.toList())
//                    mPlayTable.keys.toList()[1].let {
//                        loadTypeContent(it)
//                    }
                }, {
                    Log.e(TAG, "start: ", it)
                })
    }

    override fun loadTypeContent(key: String) {
        Log.d(TAG, "loadTypeContent() called with: key = [ $key ]")
        mPlayTable[key]?.let {
            Log.d(TAG, "loadTypeContent: size = " + it.size)
            mView.showMovieList(it)
        }
    }

    override fun search(word: String) {
        val searchList: MutableList<MovieItem> = mutableListOf()

        for (miList in mPlayTable.values) {
            for (mi in miList) {
                mi.title?.let {
                    if (it.contains(word)) {
                        searchList.add(mi)
                    }
                }
            }
        }

        mView.showMovieList(searchList)
    }
}