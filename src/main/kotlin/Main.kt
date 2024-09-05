import com.thoughtworks.kotlin_basic.products.ProductInfoClient
import com.thoughtworks.kotlin_basic.products.ProductService

fun main(args: Array<String>) {

    val productInfoList = ProductService(ProductInfoClient().getProductInfoClient()).getProductInfo()

    for (productWithQuantity in productInfoList) {
        println(productWithQuantity)
    }

}