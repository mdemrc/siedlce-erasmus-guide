package com.siedlce.erasmusguide.ui.map

import androidx.lifecycle.ViewModel
import com.siedlce.erasmusguide.data.model.Poi
import com.siedlce.erasmusguide.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    private val _pois = MutableStateFlow<List<Poi>>(emptyList())
    val pois: StateFlow<List<Poi>> = _pois

    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory

    val categories = listOf(
        "university", "dormitory", "food", "grocery",
        "health", "transport", "finance", "recreation"
    )

    init {
        _pois.value = repository.loadPois()
    }

    fun filterByCategory(category: String?) {
        _selectedCategory.value = category
    }

    fun getFilteredPois(): List<Poi> {
        val category = _selectedCategory.value
        return if (category == null) _pois.value
        else _pois.value.filter { it.category == category }
    }
}
