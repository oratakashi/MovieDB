package com.oratakashi.oratamovie.data.db.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oratakashi.oratamovie.data.model.discover.DataDiscover
import io.reactivex.Single

@Dao
interface PopularDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addAll(data : List<DataDiscover>) : Single<List<Long>>

    @Query("select * from popular order by created_at asc")
    fun getAll() : DataSource.Factory<Int, DataDiscover>

    @Query("Select * from popular where title like :query order by created_at asc")
    fun search(query: String) : DataSource.Factory<Int, DataDiscover>

    @Query("Delete from popular")
    fun deleteAll() : Single<Int>
}