package com.siedlce.erasmusguide.data.repository

import android.content.Context
import com.siedlce.erasmusguide.data.model.ChecklistItem
import com.siedlce.erasmusguide.data.model.Poi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val json = Json { ignoreUnknownKeys = true }

    fun loadPois(): List<Poi> {
        val jsonString = context.assets.open("pois.json").bufferedReader().use { it.readText() }
        return json.decodeFromString(jsonString)
    }

    fun loadChecklist(): List<ChecklistItem> {
        val jsonString = context.assets.open("checklist.json").bufferedReader().use { it.readText() }
        return json.decodeFromString(jsonString)
    }
}
