package com.example.interviewpurpose.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.interviewpurpose.MoviesDetails
import com.example.interviewpurpose.R
import com.example.interviewpurpose.retrofit.SearchItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movielistitem.view.*

class MovieListAdapter(val context:Context,val searchItem: MutableList<SearchItem>) :RecyclerView.Adapter<MovieListAdapter.ChildHolder>() {
    class ChildHolder(view: View):RecyclerView.ViewHolder(view) {
    var moviePoster=view.imageView
        var movieName =view.textView
        var ratting = view.textView2
        var year = view.textView3
        var dec = view.textView4
        var parentLayour = view.parentLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildHolder {
        val inflater = LayoutInflater.from(parent.context)
    return ChildHolder(inflater.inflate(R.layout.movielistitem,parent,false))
    }

    override fun getItemCount(): Int {
    return searchItem.size
    }

    override fun onBindViewHolder(holder: ChildHolder, position: Int) {
        holder.movieName.text =  searchItem[position].title
        holder.dec.text= searchItem[position].type
        holder.ratting.text=searchItem[position].imdbID
        holder.year.text=searchItem[position].year
        Picasso.get().load(searchItem[position].poster).into(holder.moviePoster)
        holder.parentLayour.setOnClickListener {
            val intent = Intent(context, MoviesDetails::class.java)
            intent.putExtra("movieName", searchItem[position].title)
            context.startActivity(intent)
        }
    }
}