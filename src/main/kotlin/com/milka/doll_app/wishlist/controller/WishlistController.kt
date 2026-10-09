package com.milka.doll_app.wishlist.controller

import com.milka.doll_app.wishlist.dto.CreateItemRequest
import com.milka.doll_app.wishlist.dto.UpdateItemStatusRequest
import com.milka.doll_app.wishlist.model.Item
import com.milka.doll_app.wishlist.service.WishlistService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

/**
 * Контроллер для работы с вишлистом
 */
@RestController
@RequestMapping("/api/wishlist")
class WishlistItemController(
    private val wishlistService: WishlistService
) {

    /**
     * Получение пункта по id
     */
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Item = wishlistService.getById(id)


    /**
     * Создание пункта вишлиста
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @RequestBody request: CreateItemRequest
    ): Item {
        return wishlistService.create(request)
    }

    /**
     * Полный список вишлиста
     */
    @GetMapping
    fun getAll(): List<Item> = wishlistService.getAll()

    @PatchMapping("/{id}/status")
    fun updateStatus(
        @PathVariable id: Long,
        @RequestBody request: UpdateItemStatusRequest
    ): Item {
        return wishlistService.updateStatus(id, request.status)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Void> {
        wishlistService.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}

