package com.oratakashi.oratamovie.data.model.discover

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "popular")
data class DataDiscover (
    @SerializedName("backdrop_path") val backdrop_path : String?,
    @SerializedName("id") @PrimaryKey(autoGenerate = false) val id : Int,
    @SerializedName("overview") val overview : String?,
    @SerializedName("poster_path") val poster_path : String?,
    @SerializedName("title") val title : String?,
    @SerializedName("release_date") val release_date : String?,
    @SerializedName("created_at") val created_at : String? = null
)