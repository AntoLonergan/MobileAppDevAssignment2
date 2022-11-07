package org.wit.foodReview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.wit.foodreview.databinding.ActivityReviewBinding
import timber.log.Timber
import timber.log.Timber.i

class ReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())

        i("Review Activity started...")

        binding.btnAdd.setOnClickListener() {
            val reviewTitle = binding.reviewTitle.text.toString()
            if (reviewTitle.isNotEmpty()) {
                i("add Button Pressed: $reviewTitle")
            }
            else {
                Snackbar
                    .make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}