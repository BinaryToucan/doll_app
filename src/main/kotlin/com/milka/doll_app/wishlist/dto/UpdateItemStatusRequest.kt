package com.milka.doll_app.wishlist.dto

import com.milka.doll_app.wishlist.model.ItemStatus

/**
 * Обновление статуса
 */
data class UpdateItemStatusRequest(
    val status: ItemStatus
)