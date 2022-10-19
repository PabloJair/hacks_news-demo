package com.pabloangeles.hackernewsapp.core.local.entities.converts

import androidx.room.TypeConverter
import com.pabloangeles.hackernewsapp.core.local.entities.NewsHitsStatus

object RoomConverts {

    /* Converting the value of the enum to an int. */
    @TypeConverter
    @JvmStatic
    fun toNewsHitsEntityStatus(value: Int): NewsHitsStatus = enumValues<NewsHitsStatus>()[value]

    /* Converting the value of the enum to an int. */
    @TypeConverter
    @JvmStatic
    fun fromNewsHitsEntityStatus(value: NewsHitsStatus): Int = value.ordinal
}