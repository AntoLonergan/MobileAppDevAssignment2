package org.wit.foodReview.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class ReviewMemStore : ReviewStore {

    val reviews = ArrayList<ReviewModel>()

    override fun findAll(): List<ReviewModel> {
        return reviews
    }

    override fun create(review: ReviewModel) {
        review.id = getId()
        reviews.add(review)
        logAll()
    }

    override fun update(review: ReviewModel) {
        val foundReview: ReviewModel? = reviews.find { p -> p.id == review.id }
        if (foundReview != null) {
            foundReview.name = review.name
            foundReview.address = review.address
            foundReview.postCode = review.postCode
            foundReview.justEat = review.justEat
            foundReview.items = review.items
            foundReview.price = review.price
            foundReview.comments = review.comments
            foundReview.rating = review.rating
            foundReview.image = review.image
            foundReview.lat = review.lat
            foundReview.lng = review.lng
            foundReview.zoom = review.zoom
            logAll()
        }
    }

    private fun logAll() {
        reviews.forEach { i("$it") }
    }
}