package com.milka.doll_app.wishlist.repository

import com.milka.doll_app.wishlist.model.Item
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Репозиторий для работы с элементами списка желаний.
 *
 * Spring Data JPA автоматически предоставляет CRUD-операции
 */
interface WishlistRepository : JpaRepository<Item, Long>