package com.thoughtworks.kotlin_basic.products.models

data class ProductWithQuantity(
    val SKU: String,
    val name: String,
    val price: Double,
    val quantity: Int,
    val imageUrl: String
)
