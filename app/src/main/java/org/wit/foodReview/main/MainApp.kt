package org.wit.foodReview.main

import android.app.Application
import org.wit.foodReview.models.ReviewModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val reviews = ArrayList<ReviewModel>()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Review started")
    }
}