package com.pabloangeles.hackernewsapp.core.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pabloangeles.hackernewsapp.core.local.dao.NewsHitsDao
import com.pabloangeles.hackernewsapp.core.local.entities.NewsHitsEntity
import com.pabloangeles.hackernewsapp.core.local.entities.converts.RoomConverts


@Database(entities = [NewsHitsEntity::class], version = 1)
@TypeConverters(RoomConverts::class)
abstract class HackerNewsDatabase:RoomDatabase() {
    /**
     * This function is an abstract function that returns a NewsHitsDao object
     */
    abstract fun newsHitsEntityDao(): NewsHitsDao

    companion object {
        /* It's a variable that is volatile, which means that it can be accessed from multiple threads. */
        @Volatile private var instance: HackerNewsDatabase? = null

        /**
         * If the instance is null, then create a new database and assign it to the instance
         *
         * @param context The application context.
         */
        fun getDatabase(context: Context): HackerNewsDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        /**
         * We're building a database using Room, and we're telling it to use the HackerNewsDatabase
         * class as the database, and we're telling it to use the name "hackerNewsDB" for the database
         *
         * @param appContext The application context.
         */
        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, HackerNewsDatabase::class.java, "hackerNewsDB")
                .fallbackToDestructiveMigration()
                .build()
    }

}