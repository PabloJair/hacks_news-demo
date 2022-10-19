package com.pabloangeles.hackernewsapp.core.network


import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import okhttp3.internal.platform.Platform
import okhttp3.internal.platform.Platform.Companion.INFO
import okhttp3.internal.platform.Platform.Companion.WARN

import okhttp3.logging.HttpLoggingInterceptor


 class PrettyLogger : HttpLoggingInterceptor.Logger {
    private val gson = GsonBuilder().setPrettyPrinting().create()
    private val jsonParser = JsonParser()
     override fun log(message: String) {
        val trimMessage = message.trim()
         if ((trimMessage.startsWith(prefix = "{") &&
                    trimMessage.endsWith(suffix = "}")) ||
            (trimMessage.startsWith(prefix = "[") &&
                    trimMessage.endsWith(suffix = "]"))
        ) {
            try {
                val prettyJson: String = gson.toJson(jsonParser.parse(message))
                Platform.get().log(prettyJson,INFO)
            } catch (e: Exception) {
                Platform.get().log( message, WARN, e)
            }
        } else {
            Platform.get().log(message, INFO)
        }
    }

}