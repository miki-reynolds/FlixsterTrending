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


private const val TAG = "TrendingShowAdapter"
const val SHOW_EXTRA = "SHOW_EXTRA"


class NowTrendingShowRecyclerViewAdapter(
    private val context: Context,
    private val shows: List<Show>,
    private val showListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<NowTrendingShowRecyclerViewAdapter.ShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
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
        return ShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        val show = shows[position]
        holder.bind(show)
    }

    override fun getItemCount() = shows.size

    inner class ShowViewHolder(val showView: View) : RecyclerView.ViewHolder(showView),
        View.OnClickListener {
        var showItem: Show? = null
        // we already declare the type here, so no need to declare "as TextView,"
        // which helps compiler treat it as TV instead of a generic View - good practice.
        private val showImage: ImageView = showView.findViewById<View>(R.id.item_media_image) as ImageView
        private val showTitle: TextView = showView.findViewById<View>(R.id.item_media_title) as TextView

        init {
            showView.setOnClickListener(this)
        }

        // helper method to help set up the onBindViewHolder method
        // another way of doing the content of this is just put it inside onBindViewHolder
        fun bind(show: Show) {
            showTitle.text = show.title

            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500" + show.poster)
                .into(showImage)
        }

        override fun onClick(v: View?) {
            // get selected article
            val show = shows[absoluteAdapterPosition]
            // navigate to Details screen and pass selected article
            // first parameter is the context, second is the class of the activity to launch
            val intent = Intent(context, ShowDetailActivity::class.java)
            intent.putExtra(SHOW_EXTRA, show)
            context.startActivity(intent)
        }
    }
}