package rqg.fantasy.open163.tv.di.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import rqg.fantasy.open163.tv.App
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by rqg on 02/07/2017.
 */


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(myApplication: App): OkHttpClient {
        val builder = OkHttpClient.Builder()

        val httpCacheDirectory = File(myApplication.externalCacheDir, "responses")
        val cacheSize = 200 * 1024 * 1024
        val cache = Cache(httpCacheDirectory, cacheSize.toLong())

        builder.cache(cache)
                .connectTimeout(3, TimeUnit.SECONDS)

        return builder.build()
    }


}