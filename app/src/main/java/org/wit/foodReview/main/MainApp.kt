package org.wit.foodReview.main

import android.app.Application
import org.wit.foodReview.models.ReviewMemStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    lateinit var reviews: ReviewMemStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        reviews = ReviewMemStore()
        i("Review App started")
    }
}