package com.siedlce.erasmusguide.data.repository

import com.siedlce.erasmusguide.data.local.dao.CheckedItemDao
import com.siedlce.erasmusguide.data.local.entity.CheckedItemEntity
import com.siedlce.erasmusguide.data.model.ChecklistItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Combines the static checklist content (bundled JSON) with the persisted
 * "checked" state stored in Room. UI observes [observeCheckedIds] for live
 * updates; check-state survives process death.
 */
@Singleton
class ChecklistRepository @Inject constructor(
    private val dataRepository: DataRepository,
    private val checkedItemDao: CheckedItemDao
) {

    suspend fun loadItems(): List<ChecklistItem> = dataRepository.loadChecklist()

    fun observeCheckedIds(): Flow<Set<String>> =
        checkedItemDao.observeCheckedIds().map { it.toSet() }

    suspend fun toggle(itemId: String) {
        if (checkedItemDao.isChecked(itemId)) {
            checkedItemDao.deleteById(itemId)
        } else {
            checkedItemDao.insert(
                CheckedItemEntity(itemId = itemId, checkedAt = System.currentTimeMillis())
            )
        }
    }
}
