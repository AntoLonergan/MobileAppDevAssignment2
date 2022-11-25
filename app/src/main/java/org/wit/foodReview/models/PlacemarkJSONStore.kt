package org.wit.foodReview.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import org.wit.foodReview.helpers.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "reviews.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<ReviewModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class ReviewJSONStore(private val context: Context) : ReviewStore {

    var reviews = mutableListOf<ReviewModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<ReviewModel> {
        logAll()
        return reviews
    }

    override fun create(review: ReviewModel) {
        review.id = generateRandomId()
        reviews.add(review)
        serialize()
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
    private fun serialize() {
        val jsonString = gsonBuilder.toJson(reviews, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        reviews = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        reviews.forEach { Timber.i("$it") }
    }
}

class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}