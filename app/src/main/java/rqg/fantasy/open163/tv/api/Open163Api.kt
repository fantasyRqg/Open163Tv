package rqg.fantasy.open163.tv.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import rqg.fantasy.open163.tv.model.MovieItem

/**
 * Created by rqg on 02/07/2017.
 */


interface Open163Api {
    companion object {
        //         http://mobile.open.163.com/movie/2/getPlaysForAndroid.htm
//         http://mobile.open.163.com/movie/MCMCEKO0I/getMoviesForAndroid.htm
        const val OPEN_163_URL_BASE = "http://mobile.open.163.com"
    }


    @GET("movie/2/getPlaysForAndroid.htm")
    fun getPlays(): Observable<List<MovieItem>>

    @GET("movie/{id}/getMoviesForAndroid.htm")
    fun getMovies(@Path("id") id: String): Observable<MovieItem>
}