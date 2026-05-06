package com.siedlce.erasmusguide.ui.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siedlce.erasmusguide.data.model.Poi
import com.siedlce.erasmusguide.data.repository.PoiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val poiRepository: PoiRepository
) : ViewModel() {

    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory.asStateFlow()

    private val _selectedPoiId = MutableStateFlow<String?>(null)

    val pois: StateFlow<List<Poi>> = poiRepository.observePois()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS),
            initialValue = emptyList()
        )

    /** The POI matching [_selectedPoiId], or null if nothing is selected. */
    val selectedPoi: StateFlow<Poi?> =
        combine(pois, _selectedPoiId) { items, id ->
            id?.let { items.firstOrNull { poi -> poi.id == it } }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS),
            initialValue = null
        )

    /** Distinct categories present in the loaded POI dataset. */
    val categories: StateFlow<List<String>> = pois
        .map { items -> items.map { it.category }.distinct() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS),
            initialValue = emptyList()
        )

    /** POIs filtered by [selectedCategory] (null means show all). */
    val filteredPois: StateFlow<List<Poi>> =
        combine(pois, _selectedCategory) { items, category ->
            if (category == null) items else items.filter { it.category == category }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS),
            initialValue = emptyList()
        )

    init {
        viewModelScope.launch {
            poiRepository.ensureSeeded()
            poiRepository.syncFromRemote()
        }
    }

    fun filterByCategory(category: String?) {
        _selectedCategory.value = category
    }

    fun selectPoi(id: String?) {
        _selectedPoiId.value = id
    }

    private companion object {
        const val STOP_TIMEOUT_MS = 5_000L
    }
}
