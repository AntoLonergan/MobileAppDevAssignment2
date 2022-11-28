package org.wit.foodReview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.wit.foodReview.models.ReviewModel
import org.wit.foodreview.databinding.CardReviewBinding


interface ReviewListener {
    fun onReviewClick(review: ReviewModel, position : Int)
}


class ReviewAdapter constructor(private var reviews: List<ReviewModel>,
                                   private val listener: ReviewListener
) :
    RecyclerView.Adapter<ReviewAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardReviewBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val review = reviews[holder.adapterPosition]
        holder.bind(review, listener)
    }

    override fun getItemCount(): Int = reviews.size

    class MainHolder(private val binding : CardReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(review: ReviewModel, listener: ReviewListener) {
            binding.reviewName.text = review.name
            binding.reviewAddress.text = review.address
            binding.reviewPostCode.text = review.postCode
            binding.reviewJustEat.text = review.justEat
            binding.reviewItems.text = review.items
            binding.reviewPrice.text = review.price
            binding.reviewComments.text = review.comments
            binding.reviewRating.text = review.rating
            Picasso.get().load(review.image).resize(200,200).into(binding.imageIcon)
            binding.root.setOnClickListener { listener.onReviewClick(review,adapterPosition) }
        }
    }
}

