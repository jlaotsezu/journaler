package com.journaler

import android.app.Application
import android.content.Context

class Journaler : Application(){
    companion object {
        var context: Context? = null
    }
    override fun onCreate(){
        super.onCreate()
        context = applicationContext
    }
}