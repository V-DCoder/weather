package com.apps.demo.apixuweather.view.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.demo.apixuweather.R
import com.apps.demo.apixuweather.model.Forecastday
import kotlinx.android.synthetic.main.list_item.view.day
import kotlinx.android.synthetic.main.list_item.view.temperature

import java.text.SimpleDateFormat
import java.util.*


class ForecastAdapter : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {
    private val UNIX_JAVA_MULTIPLIER = 1000L
    var forecastday: List<Forecastday>? = null
    var simpleDateFormat = SimpleDateFormat("EEEE", Locale.US)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                null
            )
        )
    }

    override fun getItemCount(): Int {
        return forecastday?.size ?: 0
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        forecastday?.run {
            holder.bindForecast(get(position))
        }

    }


    inner class ForecastViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindForecast(forecast: Forecastday) {

            val cal = Calendar.getInstance()
            cal.timeInMillis = forecast.dateEpoch * UNIX_JAVA_MULTIPLIER
            itemView.day.text = simpleDateFormat.format(cal.time)
            itemView.temperature.text =
                view.context.getString(R.string.celsius, forecast.day.avgtempC)
        }
    }
}