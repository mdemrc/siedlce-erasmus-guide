package com.siedlce.erasmusguide.data.remote

import com.siedlce.erasmusguide.data.model.Poi
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Default no-op implementation: app stays fully offline and uses the bundled
 * JSON seed cached in Room. Returning `null` signals "no remote data".
 */
@Singleton
class NoopRemotePoiSource @Inject constructor() : RemotePoiSource {
    override suspend fun fetchPois(): List<Poi>? = null
}
