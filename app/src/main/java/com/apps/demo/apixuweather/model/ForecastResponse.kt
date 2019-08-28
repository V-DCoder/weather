package com.apps.demo.apixuweather.model
import com.google.gson.annotations.SerializedName


data class ForecastResponse(
    @SerializedName("current")
    var current: Current,
    @SerializedName("forecast")
    var forecast: Forecast,
    @SerializedName("location")
    var location: Location
)

data class Forecast(
    @SerializedName("forecastday")
    var forecastday: List<Forecastday>
)

data class Forecastday(
    @SerializedName("astro")
    var astro: Astro,
    @SerializedName("date")
    var date: String,
    @SerializedName("date_epoch")
    var dateEpoch: Int,
    @SerializedName("day")
    var day: Day
)

data class Astro(
    @SerializedName("moonrise")
    var moonrise: String,
    @SerializedName("moonset")
    var moonset: String,
    @SerializedName("sunrise")
    var sunrise: String,
    @SerializedName("sunset")
    var sunset: String
)

data class Day(
    @SerializedName("avghumidity")
    var avghumidity: Double,
    @SerializedName("avgtemp_c")
    var avgtempC: Double,
    @SerializedName("avgtemp_f")
    var avgtempF: Double,
    @SerializedName("avgvis_km")
    var avgvisKm: Double,
    @SerializedName("avgvis_miles")
    var avgvisMiles: Double,
    @SerializedName("condition")
    var condition: ConditionX,
    @SerializedName("maxtemp_c")
    var maxtempC: Double,
    @SerializedName("maxtemp_f")
    var maxtempF: Double,
    @SerializedName("maxwind_kph")
    var maxwindKph: Double,
    @SerializedName("maxwind_mph")
    var maxwindMph: Double,
    @SerializedName("mintemp_c")
    var mintempC: Double,
    @SerializedName("mintemp_f")
    var mintempF: Double,
    @SerializedName("totalprecip_in")
    var totalprecipIn: Double,
    @SerializedName("totalprecip_mm")
    var totalprecipMm: Double,
    @SerializedName("uv")
    var uv: Double
)

data class Condition(
    @SerializedName("code")
    var code: Int,
    @SerializedName("icon")
    var icon: String,
    @SerializedName("text")
    var text: String
)

data class Location(
    @SerializedName("country")
    var country: String,
    @SerializedName("lat")
    var lat: Double,
    @SerializedName("localtime")
    var localtime: String,
    @SerializedName("localtime_epoch")
    var localtimeEpoch: Int,
    @SerializedName("lon")
    var lon: Double,
    @SerializedName("name")
    var name: String,
    @SerializedName("region")
    var region: String,
    @SerializedName("tz_id")
    var tzId: String
)

data class Current(
    @SerializedName("cloud")
    var cloud: Int,
    @SerializedName("condition")
    var condition: Condition,
    @SerializedName("feelslike_c")
    var feelslikeC: Double,
    @SerializedName("feelslike_f")
    var feelslikeF: Double,
    @SerializedName("gust_kph")
    var gustKph: Double,
    @SerializedName("gust_mph")
    var gustMph: Double,
    @SerializedName("humidity")
    var humidity: Int,
    @SerializedName("is_day")
    var isDay: Int,
    @SerializedName("last_updated")
    var lastUpdated: String,
    @SerializedName("last_updated_epoch")
    var lastUpdatedEpoch: Int,
    @SerializedName("precip_in")
    var precipIn: Double,
    @SerializedName("precip_mm")
    var precipMm: Double,
    @SerializedName("pressure_in")
    var pressureIn: Double,
    @SerializedName("pressure_mb")
    var pressureMb: Double,
    @SerializedName("temp_c")
    var tempC: Double,
    @SerializedName("temp_f")
    var tempF: Double,
    @SerializedName("uv")
    var uv: Double,
    @SerializedName("vis_km")
    var visKm: Double,
    @SerializedName("vis_miles")
    var visMiles: Double,
    @SerializedName("wind_degree")
    var windDegree: Int,
    @SerializedName("wind_dir")
    var windDir: String,
    @SerializedName("wind_kph")
    var windKph: Double,
    @SerializedName("wind_mph")
    var windMph: Double
)

data class ConditionX(
    @SerializedName("code")
    var code: Int,
    @SerializedName("icon")
    var icon: String,
    @SerializedName("text")
    var text: String
)