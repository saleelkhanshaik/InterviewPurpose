package com.example.interviewpurpose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interviewpurpose.adapter.MovieListAdapter
import com.example.interviewpurpose.retrofit.RetrofitApiService
import com.example.interviewpurpose.retrofit.SearchItem
import com.example.interviewpurpose.retrofit.SearchMovie
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNCHECKED_CAST")
class MainActivity : AppCompatActivity() {
    private val apiService=RetrofitApiService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieEdit.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                if(p0.toString().length > 2){
                    setDataVisibility(0)
                    callSearchAPI(p0.toString())
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
    }

    private fun callSearchAPI(toString: String) {
            val movieListCall:Call<SearchMovie> = apiService.getMoviesList(1,toString)
            movieListCall.enqueue(object :Callback<SearchMovie>{
                override fun onFailure(call: Call<SearchMovie>, t: Throwable) {
                    setDataVisibility(1)
                }

                override fun onResponse(call: Call<SearchMovie>, response: Response<SearchMovie>) {
                    setDataVisibility(1)
                    setAdapter(response.body())
                }

            })
    }

    private fun setAdapter(body: SearchMovie?) {
        body?.search.let {
            setDataVisibility(2)
            moviesList.apply{
                layoutManager=LinearLayoutManager(this@MainActivity)
                adapter =MovieListAdapter(this@MainActivity,body?.search as MutableList<SearchItem>)
            }
        }
    }

    fun setDataVisibility(pos:Int){
        when(pos){
            0 -> {
                nodataTxt.visibility=View.GONE
                moviesList.visibility = View.GONE
                progressBar.visibility=View.VISIBLE
            }
            1 -> {
                nodataTxt.visibility=View.VISIBLE
                moviesList.visibility = View.GONE
                progressBar.visibility=View.GONE
            }
            2 -> {
                nodataTxt.visibility=View.GONE
                moviesList.visibility = View.VISIBLE
                progressBar.visibility=View.GONE
            }
        }
    }
}