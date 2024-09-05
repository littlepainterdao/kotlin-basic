package com.thoughtworks.kotlin_basic.products.models

import com.google.gson.annotations.SerializedName

data class Product(
    val id: Int,
    @SerializedName("SKU")
    val productCode: String,
    val name: String,
    val price: Double,
    val type: String,
    val image: String
)
