package com.thoughtworks.kotlin_basic.products

import com.thoughtworks.kotlin_basic.products.models.Inventory
import com.thoughtworks.kotlin_basic.products.models.Product
import com.thoughtworks.kotlin_basic.products.models.ProductWithQuantity
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Response

class ProductServiceTest {

    private val mockProductClient: ProductApi = mockk<ProductApi>()

    private lateinit var service: ProductService

    @BeforeEach
    fun setUp() {
        service = ProductService(mockProductClient)
    }

    @Test
    fun `should return array of product info with quantity`() {
        val productResponse = listOf(Product(1, "ABC123", "Electronic Watch", 299.99, "NORMAL", "image1.jpg"))
        val inventoryResponse = listOf(Inventory(1, "ABC123", "CN_NORTH", 120))
        every { runBlocking { mockProductClient.getProducts() } } returns Response.success(productResponse)
        every { runBlocking { mockProductClient.getInventories() } } returns Response.success(inventoryResponse)

        val expectation = listOf(ProductWithQuantity("ABC123", "Electronic Watch", 299.99,120, "image1.jpg"))

        assertEquals(expectation, service.getProductInfo())
    }

    @Test
    fun `should return product info with sum of quantity`() {
        val productResponse = listOf(Product(1, "ABC123", "Electronic Watch", 299.99, "NORMAL", "image1.jpg"))
        val inventoryResponse = listOf(Inventory(1, "ABC123", "CN_NORTH", 120),
            Inventory(2, "ABC123", "US_WEST", 80),
            Inventory(3, "ABC456", "EU_CENTRAL", 90))
        every { runBlocking { mockProductClient.getProducts() } } returns Response.success(productResponse)
        every { runBlocking { mockProductClient.getInventories() } } returns Response.success(inventoryResponse)

        val expectation = listOf(ProductWithQuantity("ABC123", "Electronic Watch", 299.99,200, "image1.jpg"))

        assertEquals(expectation, service.getProductInfo())
    }

    @Test
    fun `should return product info with original price given low inventory and product type is NORMAL`() {
        val productResponse = listOf(Product(1, "ABC123", "Electronic Watch", 299.99, "NORMAL", "image1.jpg"))
        val inventoryResponse = listOf(Inventory(1, "ABC123", "CN_NORTH", 10))
        every { runBlocking { mockProductClient.getProducts() } } returns Response.success(productResponse)
        every { runBlocking { mockProductClient.getInventories() } } returns Response.success(inventoryResponse)

        val expectation = listOf(ProductWithQuantity("ABC123", "Electronic Watch", 299.99,10, "image1.jpg"))

        assertEquals(expectation, service.getProductInfo())
    }

    @Test
    fun `should return product info with original price given inventory more than 100 and product type is HIGH_DEMAND`() {
        val productResponse = listOf(Product(1, "ABC123", "Electronic Watch", 299.99, "HIGH_DEMAND", "image1.jpg"))
        val inventoryResponse = listOf(Inventory(1, "ABC123", "CN_NORTH", 101))
        every { runBlocking { mockProductClient.getProducts() } } returns Response.success(productResponse)
        every { runBlocking { mockProductClient.getInventories() } } returns Response.success(inventoryResponse)

        val expectation = listOf(ProductWithQuantity("ABC123", "Electronic Watch", 299.99,101, "image1.jpg"))

        assertEquals(expectation, service.getProductInfo())
    }

    @Test
    fun `should return product info with 120 percentage price given inventory between 30 and 100 and product type is HIGH_DEMAND`() {
        val productResponse = listOf(Product(1, "ABC123", "Electronic Watch", 100.0, "HIGH_DEMAND", "image1.jpg"))
        val inventoryResponse = listOf(Inventory(1, "ABC123", "CN_NORTH", 100))
        every { runBlocking { mockProductClient.getProducts() } } returns Response.success(productResponse)
        every { runBlocking { mockProductClient.getInventories() } } returns Response.success(inventoryResponse)

        val expectation = listOf(ProductWithQuantity("ABC123", "Electronic Watch", 120.0,100, "image1.jpg"))

        assertEquals(expectation, service.getProductInfo())
    }

    @Test
    fun `should return product info with 150 percentage price given inventory less than 30 and product type is HIGH_DEMAND`() {
        val productResponse = listOf(Product(1, "ABC123", "Electronic Watch", 100.0, "HIGH_DEMAND", "image1.jpg"))
        val inventoryResponse = listOf(Inventory(1, "ABC123", "CN_NORTH", 30))
        every { runBlocking { mockProductClient.getProducts() } } returns Response.success(productResponse)
        every { runBlocking { mockProductClient.getInventories() } } returns Response.success(inventoryResponse)

        val expectation = listOf(ProductWithQuantity("ABC123", "Electronic Watch", 150.0,30, "image1.jpg"))

        assertEquals(expectation, service.getProductInfo())
    }

}