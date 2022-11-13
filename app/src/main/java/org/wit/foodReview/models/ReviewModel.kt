package org.wit.foodReview.models

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
                       var rating: String = ""
) : Parcelable
