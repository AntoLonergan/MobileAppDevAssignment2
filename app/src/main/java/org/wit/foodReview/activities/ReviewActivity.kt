package org.wit.foodReview.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import org.wit.foodReview.helpers.showImagePicker
import org.wit.foodReview.main.MainApp
import org.wit.foodReview.models.ReviewModel
import org.wit.foodreview.R
import org.wit.foodreview.databinding.ActivityReviewBinding
import timber.log.Timber.i


class ReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReviewBinding
    var review = ReviewModel()
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    //val reviews = ArrayList<ReviewModel>()
    lateinit var app: MainApp
    private lateinit var mapIntentLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var edit = false
        binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp

        i("Review Activity started...")

        if (intent.hasExtra("review_edit")) {
            edit = true
            review = intent.extras?.getParcelable("review_edit")!!
            binding.reviewName.setText(review.name)
            binding.reviewAddress.setText(review.address)
            binding.reviewPostCode.setText(review.postCode)
            binding.reviewJustEat.setText(review.justEat)
            binding.reviewItems.setText(review.items)
            binding.reviewPrice.setText(review.price)
            binding.reviewComments.setText(review.comments)
            binding.reviewRating.setText(review.rating)
            binding.btnAdd.setText(R.string.save_review)
            Picasso.get()
                .load(review.image)
                .into(binding.placemarkImage)
            if (review.image != Uri.EMPTY) {
                binding.chooseImage.setText(R.string.change_review_image)
            }
        }

        binding.reviewLocation.setOnClickListener {
            i ("Set Location Pressed")
        }


        binding.btnAdd.setOnClickListener() {
            review.name = binding.reviewName.text.toString()
            review.address = binding.reviewAddress.text.toString()
            review.postCode = binding.reviewPostCode.text.toString()
            review.justEat = binding.reviewJustEat.text.toString()
            review.items = binding.reviewItems.text.toString()
            review.price = binding.reviewPrice.text.toString()
            review.comments = binding.reviewComments.text.toString()
            review.rating = binding.reviewRating.text.toString()
            if (review.name.isEmpty() ||
                review.address.isEmpty() ||
                review.postCode.isEmpty() ||
                review.justEat.isEmpty() ||
                review.items.isEmpty() ||
                review.price.isEmpty() ||
                review.comments.isEmpty() ||
                review.rating.isEmpty()
            ) {
                Snackbar
                    .make(it, R.string.enter_all_fields, Snackbar.LENGTH_LONG)
                    .show()
            }
            else {
                if (edit) {
                    app.reviews.update(review.copy())
                } else {
                    app.reviews.create(review.copy())
                }
                setResult(RESULT_OK)
                finish()
            }
        }
        binding.chooseImage.setOnClickListener {
            showImagePicker(imageIntentLauncher)
        }

        binding.reviewLocation.setOnClickListener {
            val launcherIntent = Intent(this, MapActivity::class.java)
            mapIntentLauncher.launch(launcherIntent)
        }

        registerImagePickerCallback()
        registerMapCallback()
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

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")
                            review.image = result.data!!.data!!
                            Picasso.get()
                                .load(review.image)
                                .into(binding.placemarkImage)
                            binding.chooseImage.setText(R.string.change_review_image)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }
    private fun registerMapCallback() {
        mapIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { i("Map Loaded") }
    }
}
