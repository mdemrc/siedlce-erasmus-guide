package com.siedlce.erasmusguide.data.repository

import android.content.Context
import com.siedlce.erasmusguide.data.model.ChecklistItem
import com.siedlce.erasmusguide.data.model.Poi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Loads bundled JSON data (POIs and checklist items) from the application's
 * `assets/` directory. All file IO runs on [Dispatchers.IO] so callers can
 * invoke these functions from any coroutine scope without blocking the main
 * thread.
 */
@Singleton
class DataRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun loadPois(): List<Poi> = withContext(Dispatchers.IO) {
        val text = context.assets.open(POIS_FILE).bufferedReader().use { it.readText() }
        json.decodeFromString(text)
    }

    suspend fun loadChecklist(): List<ChecklistItem> = withContext(Dispatchers.IO) {
        val text = context.assets.open(CHECKLIST_FILE).bufferedReader().use { it.readText() }
        json.decodeFromString(text)
    }

    private companion object {
        const val POIS_FILE = "pois.json"
        const val CHECKLIST_FILE = "checklist.json"
    }
}
