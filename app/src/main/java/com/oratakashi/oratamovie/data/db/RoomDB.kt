package com.oratakashi.oratamovie.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oratakashi.oratamovie.data.db.dao.FavDao
import com.oratakashi.oratamovie.data.db.dao.PopularDao
import com.oratakashi.oratamovie.data.model.discover.DataDiscover
import com.oratakashi.oratamovie.data.model.fav.DataFav

@Database(
    entities = [
        DataDiscover::class,
        DataFav::class
    ],
    version = 1
)
abstract class RoomDB : RoomDatabase() {

    abstract fun popular(): PopularDao
    abstract fun fav() : FavDao

    companion object {
        @Volatile
        private var instance: RoomDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            RoomDB::class.java,
            "OrataMovie.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}