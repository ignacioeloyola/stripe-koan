package com.ignacioeloyola.stripe_koan

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Represents a single line item for purchase in this store.
 */
@Parcelize
internal data class StoreLineItem(
    val description: String,
    val quantity: Int,
    val unitPrice: Long,
    val isProduct: Boolean = true
) : Parcelable {

    constructor(product: Product, quantity: Int) : this(
        product.emoji,
        quantity,
        product.price.toLong(),
        true
    )

    internal val totalPrice: Long
        get() = unitPrice * quantity
}
