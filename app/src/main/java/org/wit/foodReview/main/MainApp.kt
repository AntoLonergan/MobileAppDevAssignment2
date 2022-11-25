package org.wit.foodReview.main

import android.app.Application
import org.wit.foodReview.models.ReviewJSONStore
import org.wit.foodReview.models.ReviewStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    lateinit var reviews: ReviewStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        reviews = ReviewJSONStore(applicationContext)
        i("Review App started")
    }
}