package com.ems.oppidu.utilities

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.oppidu.oppidu.R
import com.ems.oppidu.util.Constant
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

object BindingAdapters {

    @JvmStatic
    @BindingAdapter(value = ["imageUrl", "defaultImage"], requireAll = false)
    fun loadImage(view: ImageView, url: String?, defaultImage: Int?) {
        val image = if (defaultImage == 0 || defaultImage == null)
            R.mipmap.icon_noimage
        else defaultImage

        if (url?.isEmpty() == true) {
            Picasso.get().load(image).into(view)
        } else {
            Picasso.get().load(url).placeholder(image).error(image).memoryPolicy(MemoryPolicy.NO_CACHE).into(view)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["imagePath", "defaultImage"], requireAll = false)
    fun loadImageFromPath(view: ImageView, path: String?, defaultImage: Int?) {
        /*if (path.isNullOrEmpty()){
            view.setImageDrawable(null)
            return
        }*/
        //Util.log(defaultImage.toString())
        val image = if (defaultImage == 0 || defaultImage == null) R.mipmap.icon_noimage
        else defaultImage

        if (path?.isEmpty() == true) {
            Picasso.get().load(image).into(view)
        } else {
            Picasso.get().load(path).placeholder(image).error(image).into(view)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["dateStr", "parseFrom"], requireAll = true)
    fun loadDate(view: TextView, dateStr: String?, format: String?) {
        if (dateStr.isNullOrEmpty()) return
        var result: String
        try {
            val apiFormat = SimpleDateFormat(format, Locale.getDefault())
            val displayFormat =
                SimpleDateFormat(Constant.DISPLAY_DATE_FORMAT_3, Locale.getDefault())
            val date = apiFormat.parse(dateStr)
            result = displayFormat.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
            result = ""
        }

        view.text = result
    }

    /*  @JvmStatic
      @BindingAdapter(value = ["date", "format"], requireAll = true)
      fun loadDate1(view: TextView, date : Date?, format : String?) {
          val result = date?.format(format!!) ?: ""
          view.text = result
      }*/

}