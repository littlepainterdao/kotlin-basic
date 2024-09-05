package com.thoughtworks.kotlin_basic.products

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductInfoClient {
    private val BASE_URL = "http://localhost:3000"

    fun getProductInfoClient() : ProductApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApi::class.java)
    }
}