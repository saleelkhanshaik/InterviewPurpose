package com.example.interviewpurpose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.interviewpurpose.retrofit.DetailedResponse
import com.example.interviewpurpose.retrofit.RetrofitApiService
import com.example.interviewpurpose.retrofit.SearchMovie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movies_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesDetails : AppCompatActivity() {
    private val apiService= RetrofitApiService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_details)
       val movieName= intent.getStringExtra("movieName")
        callAPI(movieName)
    }
    fun callAPI(movieName:String){
//        http://www.omdbapi.com/?plot=full&apikey=5d81e1ce&t=Guardians
        val movieListCall: Call<DetailedResponse> = apiService.getDeatils(movieName)
        movieListCall.enqueue(object : Callback<DetailedResponse> {
            override fun onFailure(call: Call<DetailedResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<DetailedResponse>, response: Response<DetailedResponse>) {
                updateUI(response.body())
            }

        })
    }
    fun updateUI(response:DetailedResponse?){
        Picasso.get().load(response?.poster).into(imageView2)
        textView5.text= response?.actors
        textView6.text= response?.awards
        textView7.text=response?.language
        textView8.text=response?.production
        textView9.text=response?.plot

    }
}