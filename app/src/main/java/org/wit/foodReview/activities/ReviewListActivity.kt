package org.wit.foodReview.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.wit.foodReview.main.MainApp
import org.wit.foodreview.R


class ReviewListActivity : AppCompatActivity() {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_list)
        app = application as MainApp
    }
}