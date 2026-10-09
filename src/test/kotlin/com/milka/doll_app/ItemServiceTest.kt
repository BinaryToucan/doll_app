package com.milka.doll_app

import com.milka.doll_app.wishlist.model.Item
import com.milka.doll_app.wishlist.model.ItemCategory
import com.milka.doll_app.wishlist.model.ItemStatus
import com.milka.doll_app.wishlist.repository.WishlistRepository
import com.milka.doll_app.wishlist.service.WishlistService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.web.server.ResponseStatusException
import java.time.Instant
import java.util.Optional

class ItemServiceTest {

    private lateinit var itemRepository: WishlistRepository
    private lateinit var wishlistService: WishlistService

    @BeforeEach
    fun setUp() {
        itemRepository = mockk()
        wishlistService = WishlistService(itemRepository)
    }

    private fun createItem(id: Long = 1L) = Item(
        id = id,
        name = "Barbie",
        description = "Collector doll",
        category = ItemCategory.DOLL,
        status = ItemStatus.Want,
        url = "https://example.com/barbie",
        createdAt = Instant.parse("2026-01-01T10:00:00Z"),
        updatedAt = Instant.parse("2026-01-01T10:00:00Z")
    )

    @Test
    fun `should return item by id`() {
        val item = createItem()

        every { itemRepository.findById(1L) } returns Optional.of(item)

        val result = wishlistService.getById(1L)

        assertEquals(item, result)
        verify(exactly = 1) { itemRepository.findById(1L) }
    }

    @Test
    fun `should throw 404 when item does not exist`() {
        every { itemRepository.findById(99L) } returns Optional.empty()

        val exception = assertThrows(ResponseStatusException::class.java) {
            wishlistService.getById(99L)
        }

        assertEquals(404, exception.statusCode.value())
    }

    @Test
    fun `should return all items`() {
        val items = listOf(createItem(1L), createItem(2L))

        every { itemRepository.findAll() } returns items

        val result = wishlistService.getAll()

        assertEquals(2, result.size)
        assertEquals(items, result)
    }

    @Test
    fun `should update item status`() {
        val item = createItem()

        every { itemRepository.findById(1L) } returns Optional.of(item)
        every { itemRepository.save(any()) } answers { firstArg() }

        val result = wishlistService.updateStatus(1L, ItemStatus.Ordered)

        assertEquals(ItemStatus.Ordered, result.status)
        assertTrue(result.updatedAt >= item.createdAt)

        verify(exactly = 1) { itemRepository.save(item) }
    }

    @Test
    fun `should delete existing item`() {
        every { itemRepository.existsById(1L) } returns true
        every { itemRepository.deleteById(1L) } returns Unit

        wishlistService.deleteById(1L)

        verify(exactly = 1) { itemRepository.deleteById(1L)}
    }
}