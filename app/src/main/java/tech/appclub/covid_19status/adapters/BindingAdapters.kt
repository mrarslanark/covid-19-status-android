package tech.appclub.covid_19status.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("app:setImage")
fun setImage(view: ImageView, image: String) {
    Glide.with(view.context).load(image).into(view)
}

@BindingAdapter("app:setDate")
fun setDate(view: TextView, timestamp: Long) {
    view.text = String.format("Last updated: %s", getDate(timestamp))
}

@BindingAdapter("app:setText")
fun setText(view: TextView, value: Long) {
    val decimalFormat = DecimalFormat.getInstance()
    view.text = decimalFormat.format(value).toString()
}


private fun getDate(timestamp: Long): String {
    val simpleDateFormat = SimpleDateFormat("EEE, MMM dd, yyyy", Locale.ENGLISH)
    val date = Date(timestamp)
    return simpleDateFormat.format(date).toString()
}