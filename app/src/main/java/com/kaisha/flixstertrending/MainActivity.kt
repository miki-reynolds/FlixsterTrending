package com.kaisha.flixstertrending

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import kotlinx.serialization.json.Json
import com.kaisha.flixstertrending.databinding.ActivityMainBinding


private const val TAG = "MainActivity/"
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

class MainActivity : AppCompatActivity() {
    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var showsRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private val movies = mutableListOf<Media>()
    private val shows = mutableListOf<Media>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // new way of setting up the layout instead of the traditional way of findingID
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}