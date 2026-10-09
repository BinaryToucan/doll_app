package com.milka.doll_app.wishlist.dto

import com.milka.doll_app.wishlist.model.ItemCategory
import com.milka.doll_app.wishlist.model.ItemStatus

data class CreateItemRequest(
    val name: String,
    val description: String? = null,
    val category: ItemCategory,
    val status: ItemStatus = ItemStatus.Want,
    val url: String
)