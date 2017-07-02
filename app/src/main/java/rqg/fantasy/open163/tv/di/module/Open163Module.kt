package rqg.fantasy.open163.tv.di.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rqg.fantasy.open163.tv.api.Open163Api
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by rqg on 02/07/2017.
 */

private const val apiName = "open163"

@Module
class Open163Module {


    @Provides
    @Singleton
    @Named(apiName)
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient) = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()


    @Provides
    @Singleton
    fun provideOpen163Api(@Named(apiName) retrofit: Retrofit) = retrofit.create(Open163Api::class.java)

}