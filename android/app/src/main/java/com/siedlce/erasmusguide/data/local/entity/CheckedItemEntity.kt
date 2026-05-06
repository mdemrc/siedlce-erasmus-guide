package com.siedlce.erasmusguide.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Persisted state for a single checked-off checklist item. Presence in this
 * table means the user ticked the item; absence means it is unchecked.
 */
@Entity(tableName = "checked_items")
data class CheckedItemEntity(
    @PrimaryKey val itemId: String,
    val checkedAt: Long
)
