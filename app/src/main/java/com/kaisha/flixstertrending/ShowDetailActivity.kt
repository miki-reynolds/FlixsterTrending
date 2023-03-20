package com.kaisha.flixstertrending

import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


private const val TAG = "ShowDetailActivity"


class ShowDetailActivity : AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_now_trending_media)

        // find the views for the screen
        mediaImageView = findViewById(R.id.detail_media_image)
        titleTextView = findViewById(R.id.detail_media_title)
        descriptionTextView = findViewById(R.id.detail_media_description)

        // get the extra from the Intent
//        val media = intent.getSerializableExtra(SHOW_EXTRA) as Sshow
        val media = intent.getParcelableExtra("SHOW_EXTRA", Show::class.java) as Show

        // set the title, byline, and abstract information from the article
        titleTextView.text = media.title
        descriptionTextView.text = media.overview
        descriptionTextView.movementMethod = ScrollingMovementMethod()

        // load the media image
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + media.poster)
            .centerInside()
            .into(mediaImageView)
    }

    // DetailActivity.kt (second screen/activity)
    fun onSubmit(view: View) {
        // closes the activity and returns to first screen
        this.finish();
    }
}