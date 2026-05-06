package com.siedlce.erasmusguide.data.repository

import com.siedlce.erasmusguide.data.local.dao.CheckedItemDao
import com.siedlce.erasmusguide.data.local.entity.CheckedItemEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.mock

class ChecklistRepositoryTest {

    private val dataRepository: DataRepository = mock()

    @Test
    fun toggle_uncheckedItem_insertsRow() = runTest {
        val dao = FakeCheckedItemDao()
        val repo = ChecklistRepository(dataRepository, dao)

        repo.toggle("visa")

        val ids = dao.observeCheckedIds().first()
        assertTrue(ids.contains("visa"))
        assertEquals(1, ids.size)
    }

    @Test
    fun toggle_checkedItem_removesRow() = runTest {
        val dao = FakeCheckedItemDao().apply {
            insert(CheckedItemEntity(itemId = "visa", checkedAt = 1L))
        }
        val repo = ChecklistRepository(dataRepository, dao)

        repo.toggle("visa")

        assertFalse(dao.observeCheckedIds().first().contains("visa"))
    }

    @Test
    fun toggle_isIdempotentOverTwoCalls() = runTest {
        val dao = FakeCheckedItemDao()
        val repo = ChecklistRepository(dataRepository, dao)

        repo.toggle("a")
        repo.toggle("a")

        assertTrue(dao.observeCheckedIds().first().isEmpty())
    }
}

private class FakeCheckedItemDao : CheckedItemDao {
    private val state = MutableStateFlow<Map<String, CheckedItemEntity>>(emptyMap())

    override fun observeCheckedIds(): Flow<List<String>> =
        state.map { it.keys.toList() }

    override suspend fun insert(item: CheckedItemEntity) {
        state.value = state.value + (item.itemId to item)
    }

    override suspend fun deleteById(id: String) {
        state.value = state.value - id
    }

    override suspend fun isChecked(id: String): Boolean =
        state.value.containsKey(id)
}
