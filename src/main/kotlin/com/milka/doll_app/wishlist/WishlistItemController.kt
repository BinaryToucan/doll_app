package com.milka.doll_app.wishlist

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Контроллер для работы с вишлистом
 */
@RestController
@RequestMapping("/api/wishlist")
class WishlistItemController(
    private val repository: WishlistItemRepository
) {

    /**
     * Создание пункт вишлиста
     */
    @PostMapping
    fun create(@RequestBody request: CreateWishlistItemRequest): WishlistItem {
        val item = WishlistItem(
            name = request.name,
            category = request.category,
            url = request.url
        )

        return repository.save(item)
    }

    /**
     * Полный список вишлиста
     */
    @GetMapping
    fun getAll(): List<WishlistItem> {
        return repository.findAll()
    }
}

data class CreateWishlistItemRequest(
    val name: String,
    val url: String,
    val category: ProductCategory,
)