package com.example.interviewpurpose.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("?type=movie&apikey=5d81e1ce")
    fun getMoviesList(@Query("page") page:Int,@Query("s") filter:String):Call<SearchMovie>
    @GET("?plot=full&apikey=5d81e1ce")
    fun getDetails(@Query("t") movie:String):Call<DetailedResponse>
}