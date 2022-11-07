package org.wit.foodReview.models

import timber.log.Timber.i

class ReviewMemStore : ReviewStore {

    val reviews = ArrayList<ReviewModel>()

    override fun findAll(): List<ReviewModel> {
        return reviews
    }

    override fun create(review: ReviewModel) {
        reviews.add(review)
    }

    fun logAll() {
        reviews.forEach{ i("${it}") }
    }
}