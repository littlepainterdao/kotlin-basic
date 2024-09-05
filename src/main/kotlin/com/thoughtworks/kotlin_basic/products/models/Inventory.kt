package com.thoughtworks.kotlin_basic.products.models

import com.google.gson.annotations.SerializedName

data class Inventory(
    val id: Int,
    @SerializedName("SKU")
    val productCode: String,
    val zone: String,
    val quantity: Int
)
