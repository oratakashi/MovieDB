package com.oratakashi.oratamovie.data.db.dao

import androidx.paging.DataSource
import androidx.room.*
import com.oratakashi.oratamovie.data.model.fav.DataFav
import io.reactivex.Single

@Dao
interface FavDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(data: DataFav) : Single<Long>

    @Query("Select * from favorite where id=:id")
    fun getById(id: String) : Single<List<DataFav>>

    @Delete
    fun delete(data: DataFav) : Single<Int>

    @Query("Select * from favorite order by created_at desc")
    fun getAll() : DataSource.Factory<Int, DataFav>

    @Query("Select * from favorite where title like :query order by created_at desc")
    fun search(query: String) : DataSource.Factory<Int, DataFav>
}