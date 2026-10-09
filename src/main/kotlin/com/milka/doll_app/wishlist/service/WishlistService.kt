package com.milka.doll_app.wishlist.service

import com.milka.doll_app.wishlist.repository.WishlistRepository
import com.milka.doll_app.wishlist.dto.CreateItemRequest
import com.milka.doll_app.wishlist.model.Item
import com.milka.doll_app.wishlist.model.ItemStatus
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import java.time.Instant

/**
 * Сервис по работе с вишлистом
 */
@Service
class WishlistService(
    private val itemRepository: WishlistRepository
) {

    fun getAll(): List<Item> = itemRepository.findAll()

    fun getById(id: Long): Item {
        return itemRepository.findById(id)
            .orElseThrow {
                ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Wishlist item with id $id not found"
                )
            }
    }

    fun create(request: CreateItemRequest): Item {
        val item = Item(
            name = request.name,
            description = request.description,
            category = request.category,
            status = request.status,
            url = request.url
        )

        return itemRepository.save(item)
    }

    @Transactional
    fun updateStatus(id: Long, status: ItemStatus): Item {
        val item = getById(id)

        item.status = status
        item.updatedAt = Instant.now()

        return itemRepository.save(item)
    }

    @Transactional
    fun deleteById(id: Long) {
        if (!itemRepository.existsById(id)) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Wishlist item with id $id not found"
            )
        }

        itemRepository.deleteById(id)
    }
}