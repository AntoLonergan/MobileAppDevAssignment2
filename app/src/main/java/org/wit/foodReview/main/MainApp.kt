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
        reviews.add(ReviewModel("Supermacs", "X42", "X54", "X54", "X54", "X54", "X54", "X54"))
        reviews.add(ReviewModel("McDonalds", "X43", "X54", "X54", "X54", "X54", "X54", "X54"))
        reviews.add(ReviewModel("Joe's", "X54", "X54", "X54", "X54", "X54", "X54", "X54"))
        reviews.add(ReviewModel("Supermacs", "X42", "X54", "X54", "X54", "X54", "X54", "X54"))
    }
}