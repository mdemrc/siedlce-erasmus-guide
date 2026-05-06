package com.siedlce.erasmusguide.ui.checklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siedlce.erasmusguide.data.model.ChecklistItem
import com.siedlce.erasmusguide.data.repository.ChecklistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChecklistViewModel @Inject constructor(
    private val repository: ChecklistRepository
) : ViewModel() {

    private val _items = MutableStateFlow<List<ChecklistItem>>(emptyList())
    val items: StateFlow<List<ChecklistItem>> = _items.asStateFlow()

    /** Items grouped by their `category`, sorted by `order` within each group. */
    val itemsByCategory: StateFlow<Map<String, List<ChecklistItem>>> = _items
        .map { list -> list.groupBy { it.category } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS),
            initialValue = emptyMap()
        )

    /** Persisted checked-state (survives process death). */
    val checkedIds: StateFlow<Set<String>> = repository.observeCheckedIds()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS),
            initialValue = emptySet()
        )

    init {
        viewModelScope.launch {
            _items.value = repository.loadItems()
                .sortedWith(compareBy({ it.category }, { it.order }))
        }
    }

    fun toggleItem(id: String) {
        viewModelScope.launch { repository.toggle(id) }
    }

    private companion object {
        const val STOP_TIMEOUT_MS = 5_000L
    }
}
