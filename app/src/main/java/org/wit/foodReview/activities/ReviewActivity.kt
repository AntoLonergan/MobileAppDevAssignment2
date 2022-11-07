package org.wit.foodReview.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.wit.foodReview.models.ReviewModel
import org.wit.foodreview.databinding.ActivityReviewBinding
import timber.log.Timber
import timber.log.Timber.i

class ReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReviewBinding
    var review = ReviewModel()
    val reviews = ArrayList<ReviewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.plant(Timber.DebugTree())
        i("Review Activity started...")

        binding.btnAdd.setOnClickListener() {
            review.title = binding.reviewTitle.text.toString()
            if (review.title.isNotEmpty()) {
                reviews.add(review.copy())
                i("add Button Pressed: ${review}")
                for (i in reviews.indices)
                { i("Review[$i]:${this.reviews[i]}") }
            }
            else {
                Snackbar.make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}