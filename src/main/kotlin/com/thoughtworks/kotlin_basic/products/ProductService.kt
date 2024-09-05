package com.thoughtworks.kotlin_basic.products

import com.thoughtworks.kotlin_basic.products.models.ProductWithQuantity
import kotlinx.coroutines.runBlocking

class ProductService(private val productClient: ProductApi) {

    fun getProductInfo() : List<ProductWithQuantity>{
        val productInventory = hashMapOf<String, Int>()
        val result = mutableListOf<ProductWithQuantity>()
        runBlocking {
            val products = productClient.getProducts().body()
            val inventories = productClient.getInventories().body()

            inventories?.forEach{
                productInventory[it.productCode] = productInventory.getOrDefault(it.productCode, 0) + it.quantity
            }
            products?.forEach{
                val quantity = productInventory.getOrDefault(it.productCode, 0)
                val price = if(it.type == "HIGH_DEMAND") {getPriceByInventory(quantity, it.price)} else it.price
                result.add(ProductWithQuantity(it.productCode, it.name, price, productInventory.getOrDefault(it.productCode, 0), it.image))
            }
        }
        return result
    }

    private fun getPriceByInventory(inventory: Int, originalPrice: Double): Double {
        return when {
            inventory > 100 -> originalPrice
            inventory in 31..100 -> originalPrice * 1.2
            else -> originalPrice * 1.5
        }
    }
}