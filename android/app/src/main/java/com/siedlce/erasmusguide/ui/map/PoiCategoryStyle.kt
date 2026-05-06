package com.siedlce.erasmusguide.ui.map

import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

/**
 * Visual mapping for POI categories defined in `docs/data_schema.md`.
 *
 * The `BitmapDescriptorFactory.HUE_*` constants are floats in the 0..360 range
 * that recolour the default Maps SDK pin. Categories not listed below fall
 * back to [BitmapDescriptorFactory.HUE_RED].
 */
internal object PoiCategoryStyle {

    private val HUES: Map<String, Float> = mapOf(
        "university" to BitmapDescriptorFactory.HUE_AZURE,
        "dormitory" to BitmapDescriptorFactory.HUE_VIOLET,
        "food" to BitmapDescriptorFactory.HUE_ORANGE,
        "grocery" to BitmapDescriptorFactory.HUE_GREEN,
        "health" to BitmapDescriptorFactory.HUE_RED,
        "transport" to BitmapDescriptorFactory.HUE_CYAN,
        "finance" to BitmapDescriptorFactory.HUE_YELLOW,
        "recreation" to 100f
    )

    fun markerIcon(category: String): BitmapDescriptor =
        BitmapDescriptorFactory.defaultMarker(HUES[category] ?: BitmapDescriptorFactory.HUE_RED)
}
