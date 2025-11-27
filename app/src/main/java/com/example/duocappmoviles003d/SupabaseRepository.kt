package com.example.duocappmoviles003d
import com.example.duocappmoviles003d.Cart.Cart
import com.example.duocappmoviles003d.Cart.CartProduct
import com.example.duocappmoviles003d.Login.User
import com.example.duocappmoviles003d.ProductCatalogue.Product
import io.github.jan.supabase.postgrest.from

class SupabaseRepository {

    // --- LOGIN ---
    suspend fun getUserByEmail(email: String): User? {
        return try {
            val users = supabase.from("users").select {
                filter { eq("email", email) }
            }.decodeList<User>()
            users.firstOrNull()
        } catch (e: Exception) {
            null
        }
    }

    // --- CATALOGUE ---
    suspend fun getProducts(): List<Product> {
        return try {
            supabase.from("products").select().decodeList<Product>()
        } catch (e: Exception) {
            emptyList()
        }
    }

    // --- CART: Obtener/Crear Carrito ---
    suspend fun getOrCreateActiveCart(userId: Long): Cart? {
        return try {
            val activeCarts = supabase.from("cart").select {
                filter {
                    eq("id_user", userId)
                    eq("status", "pending")
                }
            }.decodeList<Cart>()

            if (activeCarts.isNotEmpty()) return activeCarts.first()

            val newCart = Cart(userId = userId, status = "pending")
            supabase.from("cart").insert(newCart) { select() }.decodeSingle<Cart>()
        } catch (e: Exception) {
            null
        }
    }

    // --- CART: Agregar Producto ---
    suspend fun addProductToCart(cartId: Long, product: Product) {
        try {
            val existing = supabase.from("cart_product").select {
                filter {
                    eq("id_cart", cartId)
                    eq("id_product", product.id)
                }
            }.decodeList<CartProduct>()

            val quantity = if (existing.isNotEmpty()) existing[0].quantity + 1 else 1
            val grossPrice = product.price * quantity

            val item = CartProduct(
                cartId = cartId,
                productId = product.id,
                quantity = quantity,
                grossPrice = grossPrice
            )
            supabase.from("cart_product").upsert(item)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // --- CART: Obtener Items (Join manual) ---
    suspend fun getCartItems(cartId: Long): List<Pair<Product, Int>> {
        try {
            val cartProducts = supabase.from("cart_product").select {
                filter { eq("id_cart", cartId) }
            }.decodeList<CartProduct>()

            if (cartProducts.isEmpty()) return emptyList()

            val productIds = cartProducts.map { it.productId }
            val products = supabase.from("products").select {
                filter { isIn("id_product", productIds) }
            }.decodeList<Product>()

            return cartProducts.mapNotNull { cp ->
                val prod = products.find { it.id == cp.productId }
                if (prod != null) Pair(prod, cp.quantity) else null
            }
        } catch (e: Exception) {
            return emptyList()
        }
    }
}
