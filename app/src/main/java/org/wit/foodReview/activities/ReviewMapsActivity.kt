package org.wit.foodReview.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import org.wit.foodReview.main.MainApp
import org.wit.foodReview.models.ReviewModel
import org.wit.foodreview.databinding.ActivityReviewMapsBinding
import org.wit.foodreview.databinding.ContentReviewMapsBinding


class ReviewMapsActivity : AppCompatActivity(), GoogleMap.OnMarkerClickListener {

    private lateinit var binding: ActivityReviewMapsBinding
    private lateinit var contentBinding: ContentReviewMapsBinding
    lateinit var map: GoogleMap
    lateinit var app: MainApp


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MainApp

        binding = ActivityReviewMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        contentBinding = ContentReviewMapsBinding.bind(binding.root)
        contentBinding.mapView.onCreate(savedInstanceState)

        contentBinding.mapView.getMapAsync {
            map = it
            configureMap()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        contentBinding.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        contentBinding.mapView.onLowMemory()
    }

    override fun onPause() {
        super.onPause()
        contentBinding.mapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        contentBinding.mapView.onResume()
    }

    private fun configureMap() {
        map.uiSettings.isZoomControlsEnabled = true
        app.reviews.findAll().forEach {
            val loc = LatLng(it.lat, it.lng)
            val options = MarkerOptions().title(it.name).position(loc)
            map.addMarker(options)?.tag = it.id
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, it.zoom))
            map.setOnMarkerClickListener(this)

        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        //val review = marker.tag as ReviewModel
       // contentBinding.currentName.text = review.name
        //contentBinding.currentAddress.text = review.address
        //Picasso.get().load(review.image).into(contentBinding.currentImage)
        val foundReview: ReviewModel? = app.reviews.findAll().find { p -> p.id == marker.tag }
        contentBinding.currentName.text = foundReview?.name
        contentBinding.currentAddress.text = foundReview?.address
        contentBinding.currentPostCode.text = foundReview?.postCode
        contentBinding.currentJustEat.text = foundReview?.justEat
        contentBinding.currentItems.text = foundReview?.items
        contentBinding.currentPrice.text = foundReview?.price
        contentBinding.currentComments.text = foundReview?.comments
        contentBinding.currentRating.text = foundReview?.rating
        Picasso.get().load(foundReview?.image).into(contentBinding.currentImage)


        return false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        contentBinding.mapView.onSaveInstanceState(outState)
    }
}