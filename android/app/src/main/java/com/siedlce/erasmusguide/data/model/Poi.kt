package com.siedlce.erasmusguide.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Poi(
    val id: String,
    val name: String,
    val category: String,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val phone: String? = null,
    val website: String? = null,
    val description: String,
    val openingHours: String? = null
)
