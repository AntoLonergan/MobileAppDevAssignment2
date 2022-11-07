package org.wit.foodReview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.wit.foodreview.R
import timber.log.Timber
import timber.log.Timber.i

class ReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        Timber.plant(Timber.DebugTree())

        i("Food Review Activity started..")
    }
}