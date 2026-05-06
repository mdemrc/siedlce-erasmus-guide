package com.siedlce.erasmusguide.ui.map

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.siedlce.erasmusguide.R

/**
 * Translates a POI category key (as stored in JSON / Room) into a localized,
 * human-readable label. Unknown keys fall back to a Title-cased version of
 * the raw key so we never crash on data drift.
 */
@Composable
fun poiCategoryLabel(category: String): String {
    val resId = when (category) {
        "university" -> R.string.poi_category_university
        "dormitory" -> R.string.poi_category_dormitory
        "food" -> R.string.poi_category_food
        "grocery" -> R.string.poi_category_grocery
        "health" -> R.string.poi_category_health
        "transport" -> R.string.poi_category_transport
        "finance" -> R.string.poi_category_finance
        "recreation" -> R.string.poi_category_recreation
        else -> return category.replaceFirstChar { it.uppercase() }
    }
    return stringResource(resId)
}
