package seyyed.ali.tabatabaei.zamanakcalendar.utils


import com.google.gson.Gson
import seyyed.ali.tabatabaei.zamanakcalendar.MyApp
import seyyed.ali.tabatabaei.zamanakcalendar.model.events.Events
import java.io.IOException

internal object EventsManager {

    internal var events : Events ?= null

    init {
        try {
            val jsonString = getJsonFromAssets()
            events = Gson().fromJson(jsonString , Events::class.java)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun getJsonFromAssets(): String? {
        return try {
            MyApp.instance.assets.open("events.json").bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

}