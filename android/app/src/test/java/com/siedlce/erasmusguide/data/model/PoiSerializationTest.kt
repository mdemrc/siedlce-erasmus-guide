package com.siedlce.erasmusguide.data.model

import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class PoiSerializationTest {

    private val json = Json { ignoreUnknownKeys = true }

    @Test
    fun parses_minimal_poi_with_required_fields_only() {
        val raw = """
            [
              {
                "id": "uws_main",
                "name": "UwS Main Campus",
                "category": "university",
                "latitude": 52.1676,
                "longitude": 22.2806,
                "address": "ul. Konarskiego 2, Siedlce",
                "description": "Main campus building."
              }
            ]
        """.trimIndent()

        val pois: List<Poi> = json.decodeFromString(raw)

        assertEquals(1, pois.size)
        val poi = pois.first()
        assertEquals("uws_main", poi.id)
        assertEquals("university", poi.category)
        assertNull(poi.imageUrl)
        assertNull(poi.phone)
    }

    @Test
    fun parses_poi_with_all_optional_fields() {
        val raw = """
            [
              {
                "id": "p1",
                "name": "Place",
                "category": "food",
                "latitude": 1.0,
                "longitude": 2.0,
                "address": "addr",
                "description": "desc",
                "phone": "+48 111",
                "website": "https://example.com",
                "openingHours": "9-17",
                "imageUrl": "https://cdn.example.com/p1.jpg"
              }
            ]
        """.trimIndent()

        val poi = json.decodeFromString<List<Poi>>(raw).first()

        assertEquals("+48 111", poi.phone)
        assertEquals("https://cdn.example.com/p1.jpg", poi.imageUrl)
    }

    @Test
    fun ignores_unknown_remote_fields() {
        val raw = """
            [
              {
                "id": "p1",
                "name": "Place",
                "category": "food",
                "latitude": 1.0,
                "longitude": 2.0,
                "address": "addr",
                "description": "desc",
                "rating": 4.5,
                "tags": ["new"]
              }
            ]
        """.trimIndent()

        val pois = json.decodeFromString<List<Poi>>(raw)

        assertTrue(pois.size == 1)
        assertEquals("p1", pois.first().id)
    }
}
