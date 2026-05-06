package com.siedlce.erasmusguide.data.remote

import com.siedlce.erasmusguide.data.model.Poi

/**
 * Optional remote source for POI data.
 *
 * The default binding in production is [NoopRemotePoiSource], which keeps the
 * app fully offline (PRD: "core experience must work offline"). Swap the DI
 * binding for [FirestorePoiSource] (see template) once Firebase is wired up.
 */
interface RemotePoiSource {
    /**
     * Fetches the latest POI dataset, or returns `null` if no remote source is
     * configured / the call fails. Callers should treat `null` as "keep the
     * existing cache".
     */
    suspend fun fetchPois(): List<Poi>?
}
