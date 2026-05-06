package com.siedlce.erasmusguide.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.siedlce.erasmusguide.data.local.dao.CheckedItemDao
import com.siedlce.erasmusguide.data.local.dao.PoiDao
import com.siedlce.erasmusguide.data.local.entity.CachedPoiEntity
import com.siedlce.erasmusguide.data.local.entity.CheckedItemEntity

@Database(
    entities = [CachedPoiEntity::class, CheckedItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun poiDao(): PoiDao
    abstract fun checkedItemDao(): CheckedItemDao

    companion object {
        const val DB_NAME = "erasmus_guide.db"
    }
}
