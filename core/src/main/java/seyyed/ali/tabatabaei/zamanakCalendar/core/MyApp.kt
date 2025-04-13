package seyyed.ali.tabatabaei.zamanakCalendar.core

import android.app.Application

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