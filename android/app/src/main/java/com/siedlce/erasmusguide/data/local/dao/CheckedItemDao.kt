package com.siedlce.erasmusguide.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.siedlce.erasmusguide.data.local.entity.CheckedItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CheckedItemDao {

    @Query("SELECT itemId FROM checked_items")
    fun observeCheckedIds(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CheckedItemEntity)

    @Query("DELETE FROM checked_items WHERE itemId = :id")
    suspend fun deleteById(id: String)

    @Query("SELECT EXISTS(SELECT 1 FROM checked_items WHERE itemId = :id)")
    suspend fun isChecked(id: String): Boolean
}
