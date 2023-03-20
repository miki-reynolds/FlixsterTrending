package com.kaisha.flixstertrending

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


private const val TAG = "TrendingMovieAdapter"
const val MOVIE_EXTRA = "MOVIE_EXTRA"


class NowTrendingMovieRecyclerViewAdapter(
    private val context: Context,
    private val movies: List<Movie>,
    private val movieListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<NowTrendingMovieRecyclerViewAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val orientation = parent.context.resources.configuration.orientation
//        val layoutId = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            R.layout.fragment_now_trending_lists_land
//        } else {
//            R.layout.fragment_now_trending_lists
//        }
        // TODO: FIX THIS ORIENTATION LATER
        val layoutId = R.layout.item_media
//        val view = LayoutInflater.from(context).inflate(layoutId, parent, false)
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount() = movies.size

    inner class MovieViewHolder(val movieView: View) : RecyclerView.ViewHolder(movieView),
        View.OnClickListener {
        var movieItem: Movie? = null
        // we already declare the type here, so no need to declare "as TextView,"
        // which helps compiler treat it as TV instead of a generic View - good practice.
        private val movieImage: ImageView = movieView.findViewById<View>(R.id.item_media_image) as ImageView
        private val movieTitle: TextView = movieView.findViewById<View>(R.id.item_media_title) as TextView

        init {
            movieView.setOnClickListener(this)
        }

        // helper method to help set up the onBindViewHolder method
        // another way of doing the content of this is just put it inside onBindViewHolder
        fun bind(movie: Movie) {
            movieTitle.text = movie.title

            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500" + movie.poster)
                .into(movieImage)
        }

        override fun onClick(v: View?) {
            // get selected article
            val movie = movies[absoluteAdapterPosition]
            // navigate to Details screen and pass selected article
            // first parameter is the context, second is the class of the activity to launch
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE_EXTRA, movie)
            context.startActivity(intent)
        }
    }
}