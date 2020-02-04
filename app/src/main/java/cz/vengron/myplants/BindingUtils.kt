package cz.vengron.myplants

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.request.RequestOptions
import cz.vengron.myplants.database.Plant
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("plantImage")
fun ImageView.setPlantImage(plant: Plant?) {
    val requestOptions = RequestOptions()
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
        requestOptions.circleCrop()
    }
}
@BindingAdapter("formattedWateringTime")
fun TextView.formattedWateringTime(plant: Plant?) {
    plant?.let {
        text = SimpleDateFormat("E d.M HH:mm", Locale.getDefault())
            .format(plant.timeForWatering)
    }
}