package com.kaisha.flixstertrending

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONException


private const val TAG = "NowTrendingFragment/"
private const val SEARCH_API_KEY = BuildConfig.API_KEY
private const val MOVIE_SEARCH_URL =
    "https://api.themoviedb.org/3/trending/movie/week?api_key=${SEARCH_API_KEY}"
private const val SHOW_SEARCH_URL =
    "https://api.themoviedb.org/3/trending/tv/week?api_key=${SEARCH_API_KEY}"


fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

class NowTrendingFragment : Fragment(), OnListFragmentInteractionListener {
    /*
    * The class for the only fragment in the app, which contains the progress bar,
    * 2 recyclerView, and performs the network calls to the Movie Database API.
    */
    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var showRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) : View? {
        val view = inflater.inflate(R.layout.fragment_now_trending_lists, container, false)
        val progressBar = view.findViewById<View>(R.id.progressBar) as ContentLoadingProgressBar
        movieRecyclerView = view.findViewById<View>(R.id.movieList) as RecyclerView
        showRecyclerView = view.findViewById<View>(R.id.showList) as RecyclerView
        val context = view.context
        movieRecyclerView.layoutManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
        showRecyclerView.layoutManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
        updateAdapter(progressBar, movieRecyclerView, MOVIE_SEARCH_URL, true)
        updateAdapter(progressBar, showRecyclerView, SHOW_SEARCH_URL, false)
        return view
    }

    private fun updateAdapter(
        progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView, searchURl: String, movieFlag: Boolean
    ) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        client.get(searchURl, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch Media List: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
//                Log.i(TAG, "Successfully fetched Media List: $json")
                try {
                    // the wait for a response is over
                    progressBar.hide()

                    // parse data
                    val resultsJSON : JSONArray = json.jsonObject?.getJSONArray("results") as JSONArray
                    val mediaRawJSON : String = resultsJSON.toString()

                    // create gson to autofill data from json object
                    val gson = Gson()
                    if (movieFlag) {
                        val arrayShowType = object : TypeToken<List<Movie>>() {}.type
                        val models : List<Movie> = gson.fromJson(mediaRawJSON, arrayShowType)
                        recyclerView.adapter = NowTrendingMovieRecyclerViewAdapter(
                            requireContext(), models, this@NowTrendingFragment)
                    } else {
                        val arrayShowType = object : TypeToken<List<Show>>() {}.type
                        val models : List<Show> = gson.fromJson(mediaRawJSON, arrayShowType)
                        recyclerView.adapter = NowTrendingShowRecyclerViewAdapter(
                            requireContext(), models, this@NowTrendingFragment)
                    }

                } catch (e: JSONException) {
                    Log.e(TAG, "Exception with API: $e")
                }
            }
        })
    }


    override fun onItemClick(item: Movie) {
        /*
        * show a toast when an item is clicked.
        */
        Toast.makeText(context, "test: " + item.title, Toast.LENGTH_LONG).show()
    }
}


/*
-----------------------------ABOUT LAYOUT----------------------------------
//        movieRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//            .also {
//            val dividerItemDecoration = DividerItemDecoration(context, it.orientation)
//            movieRecyclerView.addItemDecoration(dividerItemDecoration)
//        }
//        showRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//            .also {
//            val dividerItemDecoration = DividerItemDecoration(context, it.orientation)
//            movieRecyclerView.addItemDecoration(dividerItemDecoration)
//        }


----------------------ABOUT CONTEXT---------------------------------
In this case, the error occurs because this is used inside the object block of JsonHttpResponseHandler(),
which refers to the current instance of JsonHttpResponseHandler(), not the instance of the NowTrendingFragment class.
To fix this error, you can replace this with requireContext().
 */