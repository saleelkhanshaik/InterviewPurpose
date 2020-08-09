package com.example.interviewpurpose.retrofit

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApiService {
    private val BASE_URL="http://www.omdbapi.com/"
    private val api = Retrofit.Builder().baseUrl(BASE_URL).
    addConverterFactory(GsonConverterFactory.create()).
    build().create(RetrofitInterface::class.java)
    fun getMoviesList(pageNum:Int,searchword:String):Call<SearchMovie>{
        return  api.getMoviesList(pageNum,searchword)
    }
    fun getDeatils(movie:String):Call<DetailedResponse>{
        return api.getDetails(movie)
    }
}