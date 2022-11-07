package org.wit.foodReview.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.wit.foodReview.main.MainApp
import org.wit.foodReview.models.ReviewModel
import org.wit.foodreview.R
import org.wit.foodreview.databinding.ActivityReviewBinding
import timber.log.Timber.i

class ReviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReviewBinding
    var review = ReviewModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp
        i("Review Activity started...")

        binding.btnAdd.setOnClickListener() {
            review.name = binding.reviewName.text.toString()
            review.address = binding.reviewAddress.text.toString()
            review.postCode = binding.reviewPostCode.text.toString()
            review.justEat = binding.reviewJustEat.text.toString()
            review.items = binding.reviewItems.text.toString()
            review.price = binding.reviewPrice.text.toString()
            review.comments = binding.reviewComments.text.toString()
            review.rating = binding.reviewRating.text.toString()
            if (review.name.isNotEmpty() &&
                review.address.isNotEmpty() &&
                review.postCode.isNotEmpty() &&
                review.justEat.isNotEmpty() &&
                review.items.isNotEmpty() &&
                review.price.isNotEmpty() &&
                review.comments.isNotEmpty() &&
                review.rating.isNotEmpty()) {
                app.reviews.create(review.copy())
                setResult(RESULT_OK)
                finish()
            }
            else {
                Snackbar.make(it,"Please Ensure All Fields Are Filled Out.", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_review, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}