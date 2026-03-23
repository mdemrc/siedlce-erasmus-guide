package com.siedlce.erasmusguide.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ChecklistItem(
    val id: String,
    val title: String,
    val description: String,
    val category: String,
    val order: Int
)
