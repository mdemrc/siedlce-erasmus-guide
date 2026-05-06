package com.siedlce.erasmusguide.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Cached copy of a POI. The dataset is small (~50 rows) and serves as the
 * single source of truth at runtime once the bundled JSON has been imported
 * on first launch. Future remote updates (Firestore) replace rows here.
 */
@Entity(tableName = "pois")
data class CachedPoiEntity(
    @PrimaryKey val id: String,
    val name: String,
    val category: String,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val phone: String?,
    val website: String?,
    val description: String,
    val openingHours: String?,
    val imageUrl: String?
)
