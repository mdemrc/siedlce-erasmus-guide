package com.siedlce.erasmusguide.ui.checklist

import androidx.lifecycle.ViewModel
import com.siedlce.erasmusguide.data.model.ChecklistItem
import com.siedlce.erasmusguide.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ChecklistViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    private val _items = MutableStateFlow<List<ChecklistItem>>(emptyList())
    val items: StateFlow<List<ChecklistItem>> = _items

    private val _checkedIds = MutableStateFlow<Set<String>>(emptySet())
    val checkedIds: StateFlow<Set<String>> = _checkedIds

    init {
        _items.value = repository.loadChecklist().sortedWith(
            compareBy({ it.category }, { it.order })
        )
    }

    fun toggleItem(id: String) {
        val current = _checkedIds.value.toMutableSet()
        if (current.contains(id)) current.remove(id) else current.add(id)
        _checkedIds.value = current
    }

    fun getItemsByCategory(): Map<String, List<ChecklistItem>> {
        return _items.value.groupBy { it.category }
    }
}
