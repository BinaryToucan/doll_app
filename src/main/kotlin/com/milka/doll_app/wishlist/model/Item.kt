package com.milka.doll_app.wishlist.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant

/**
 * Товар из вишлиста
 */
@Entity
@Table(name = "wishlist_items")
class Item(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, length = 300)
    var name: String,

    @Column(columnDefinition = "TEXT")
    var description: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var category: ItemCategory,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: ItemStatus = ItemStatus.Want,

    @Column(name = "product_url", nullable = false, length = 1000)
    var url: String,

    @Column(name = "created_at", nullable = false)
    var createdAt: Instant = Instant.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant = Instant.now()
)