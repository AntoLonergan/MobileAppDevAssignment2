package org.wit.foodReview.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReviewModel(var id: Long = 0,
                       var name: String = "",
                       var address: String = "",
                       var postCode: String = "",
                       var justEat: String = "",
                       var items: String = "",
                       var price: String = "",
                       var comments: String = "",
                       var rating: String = "",
                       var image: Uri = Uri.EMPTY,
                       var lat : Double = 0.0,
                       var lng: Double = 0.0,
                       var zoom: Float = 0f) : Parcelable

@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable