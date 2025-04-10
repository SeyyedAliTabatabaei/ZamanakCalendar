package seyyed.ali.tabatabaei.zamanakcalendar

import android.app.Application
import android.content.res.Configuration

internal class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: MyApp
            private set
    }
}