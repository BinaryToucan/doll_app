package com.milka.doll_app.wishlist

import jakarta.persistence.*
import java.time.Instant

/**
 * Товар из вишлиста
 */
@Entity
@Table(name = "wishlist_items")
class WishlistItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, length = 300)
    var name: String,

    @Column(columnDefinition = "TEXT")
    var description: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var category: ProductCategory,

    @Column(name = "product_url", nullable = false, length = 1000)
    var url: String,

    @Column(name = "created_at", nullable = false)
    var createdAt: Instant = Instant.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant = Instant.now()
)