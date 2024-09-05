package com.thoughtworks.kotlin_basic.products

import com.thoughtworks.kotlin_basic.products.models.Inventory
import com.thoughtworks.kotlin_basic.products.models.Product
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getProducts(): Response<List<Product>>

    @GET("inventories")
    suspend fun getInventories(): Response<List<Inventory>>
}