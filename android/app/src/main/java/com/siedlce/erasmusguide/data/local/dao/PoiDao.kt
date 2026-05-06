package com.siedlce.erasmusguide.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.siedlce.erasmusguide.data.local.entity.CachedPoiEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PoiDao {

    @Query("SELECT * FROM pois ORDER BY category, name")
    fun observeAll(): Flow<List<CachedPoiEntity>>

    @Query("SELECT COUNT(*) FROM pois")
    suspend fun count(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<CachedPoiEntity>)

    @Query("DELETE FROM pois")
    suspend fun clear()
}
