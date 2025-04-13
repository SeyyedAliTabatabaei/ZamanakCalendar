package seyyed.ali.tabatabaei.zamanakCore.utils


import com.google.gson.Gson
import seyyed.ali.tabatabaei.zamanakCore.MyApp
import seyyed.ali.tabatabaei.zamanakCore.model.events.Events
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