package cz.vengron.myplants

import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import cz.vengron.myplants.database.Plant
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("plantImage")
fun ImageView.setPlantImage(plant: Plant?) {
    plant?.let {
        val imgUri = plant.imageUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
                    .centerCrop()
            )
            .into(this)
    }
}

@BindingAdapter("formattedWateringTime")
fun TextView.formattedWateringTime(plant: Plant?) {
    plant?.let {
        text = SimpleDateFormat("E d.M HH:mm", Locale.getDefault())
            .format(plant.timeForWatering)
    }
}

@BindingAdapter("linkToWiki")
fun TextView.linkToWiki(plant: Plant?) {
    plant?.let {
        val link = SpannableString("Wiki")
        link.setSpan(URLSpan(plant.wikiUrl), 0, 4, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        this.movementMethod = LinkMovementMethod.getInstance()
        text = link
    }
}