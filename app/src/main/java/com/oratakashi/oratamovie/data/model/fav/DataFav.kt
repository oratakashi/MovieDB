package com.oratakashi.oratamovie.data.model.fav

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class DataFav(
    @PrimaryKey(autoGenerate = false)
    val id : String,
    val backdrop_path : String?,
    val overview : String?,
    val poster_path : String?,
    val title : String?,
    val release_date : String?,
    val created_at : String? = null
)
