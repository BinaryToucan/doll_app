package com.milka.doll_app.wishlist

import org.springframework.data.jpa.repository.JpaRepository

/**
 * Репозиторий для работы с элементами списка желаний.
 *
 * Spring Data JPA автоматически предоставляет CRUD-операции
 */
interface WishlistItemRepository : JpaRepository<WishlistItem, Long>
