package seyyed.ali.tabatabaei.zamanakCore

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