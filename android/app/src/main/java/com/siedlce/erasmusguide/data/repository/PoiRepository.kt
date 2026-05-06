package com.siedlce.erasmusguide.data.repository

import com.siedlce.erasmusguide.data.local.dao.PoiDao
import com.siedlce.erasmusguide.data.local.entity.CachedPoiEntity
import com.siedlce.erasmusguide.data.model.Poi
import com.siedlce.erasmusguide.data.remote.RemotePoiSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Single source of truth for POIs at runtime.
 *
 * On first launch (empty database), the bundled JSON is imported into Room.
 * After that, all reads stream from the database, which lets future remote
 * sources (e.g. Firestore in Step 7) update the cache without changing UI
 * code.
 */
@Singleton
class PoiRepository @Inject constructor(
    private val dataRepository: DataRepository,
    private val poiDao: PoiDao,
    private val remoteSource: RemotePoiSource
) {

    fun observePois(): Flow<List<Poi>> =
        poiDao.observeAll().map { rows -> rows.map { it.toDomain() } }

    /** Imports bundled JSON if the cache is empty. Safe to call on every launch. */
    suspend fun ensureSeeded() {
        if (poiDao.count() > 0) return
        val items = dataRepository.loadPois().map { it.toEntity() }
        poiDao.upsertAll(items)
    }

    /**
     * Best-effort remote refresh. Default binding is a no-op, so this is a
     * safe call from app startup or pull-to-refresh.
     */
    suspend fun syncFromRemote() {
        val remote = remoteSource.fetchPois() ?: return
        replaceAll(remote)
    }

    /** Replace the cached dataset, e.g. after a remote sync. */
    suspend fun replaceAll(pois: List<Poi>) {
        poiDao.upsertAll(pois.map { it.toEntity() })
    }
}

private fun CachedPoiEntity.toDomain() = Poi(
    id = id,
    name = name,
    category = category,
    latitude = latitude,
    longitude = longitude,
    address = address,
    phone = phone,
    website = website,
    description = description,
    openingHours = openingHours,
    imageUrl = imageUrl
)

private fun Poi.toEntity() = CachedPoiEntity(
    id = id,
    name = name,
    category = category,
    latitude = latitude,
    longitude = longitude,
    address = address,
    phone = phone,
    website = website,
    description = description,
    openingHours = openingHours,
    imageUrl = imageUrl
)
