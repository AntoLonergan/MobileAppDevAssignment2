package org.wit.foodReview.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.wit.foodReview.main.MainApp
import org.wit.foodReview.models.ReviewModel
import org.wit.foodreview.R
import org.wit.foodreview.databinding.ActivityReviewListBinding
import org.wit.foodreview.databinding.CardReviewBinding


class ReviewListActivity : AppCompatActivity() {
    lateinit var app: MainApp
    private lateinit var binding: ActivityReviewListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = ReviewAdapter(app.reviews)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, ReviewActivity::class.java)
                getResult.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app.reviews.size)
            }
        }


    class ReviewAdapter constructor(private var reviews: List<ReviewModel>) :
        RecyclerView.Adapter<ReviewAdapter.MainHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
            val binding = CardReviewBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

            return MainHolder(binding)
        }

        override fun onBindViewHolder(holder: MainHolder, position: Int) {
            val review = reviews[holder.adapterPosition]
            holder.bind(review)
        }

        override fun getItemCount(): Int = reviews.size

        class MainHolder(private val binding: CardReviewBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(review: ReviewModel) {
                binding.reviewName.text = review.name
                binding.reviewAddress.text = review.address
                binding.reviewPostCode.text = review.postCode
                binding.reviewJustEat.text = review.justEat
                binding.reviewItems.text = review.items
                binding.reviewPrice.text = review.price
                binding.reviewComments.text = review.comments
                binding.reviewRating.text = review.rating
            }
        }
    }
}