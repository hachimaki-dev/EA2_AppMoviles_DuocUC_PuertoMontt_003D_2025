package com.example.duocappmoviles003d
import com.example.duocappmoviles003d.Cart.Cart
import com.example.duocappmoviles003d.Cart.CartProduct
import com.example.duocappmoviles003d.Login.User
import com.example.duocappmoviles003d.ProductCatalogue.Product
import io.github.jan.supabase.postgrest.from
import android.util.Log
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
        Log.d("SupabaseRepo", "Buscando carrito activo para usuario: $userId")
        return try {
            val activeCarts = supabase.from("cart").select {
                filter {
                    eq("id_user", userId)
                    eq("status", "pending") // <-- Aquí es donde falla si no existe la columna
                }
            }.decodeList<Cart>()

            if (activeCarts.isNotEmpty()) {
                Log.d("SupabaseRepo", "Carrito existente encontrado: ${activeCarts[0].id}")
                return activeCarts.first()
            }

            Log.d("SupabaseRepo", "No hay carrito activo. Creando uno nuevo...")

            val newCart = Cart(userId = userId, status = "pending")
            val createdCart = supabase.from("cart").insert(newCart) { select() }.decodeSingle<Cart>()

            Log.d("SupabaseRepo", "Carrito nuevo creado con ID: ${createdCart.id}")
            createdCart

        } catch (e: Exception) {
            Log.e("SupabaseRepo", "ERROR CRÍTICO AL OBTENER/CREAR CARRITO: ${e.message}", e)
            null
        }
    }

    // --- CART: Agregar Producto ---
// En app/src/main/java/com/example/duocappmoviles003d/SupabaseRepository.kt

// En app/src/main/java/com/example/duocappmoviles003d/SupabaseRepository.kt

// Asegúrate de que este import esté arriba en el archivo
// ... dentro de la clase SupabaseRepository ...

    suspend fun addProductToCart(cartId: Long, product: Product) {
        try {
            // 1. Verificar si ya existe el producto en el carrito
            val existing = supabase.from("cart_product").select {
                filter {
                    eq("id_cart", cartId)
                    eq("id_product", product.id)
                }
            }.decodeList<CartProduct>()

            // 2. Calcular las variables ANTES de usarlas (para que no salgan en rojo)
            val newQuantity = if (existing.isNotEmpty()) existing[0].quantity + 1 else 1
            val newGrossPrice = product.price * newQuantity

            // 3. Crear el objeto a enviar
            val item = CartProduct(
                cartId = cartId,
                productId = product.id,
                quantity = newQuantity,     // Usamos la variable calculada arriba
                grossPrice = newGrossPrice  // Usamos la variable calculada arriba
            )

            // 4. Ejecutar upsert
            // IMPORTANTE: onConflict debe coincidir con la Primary Key que creamos en el Paso 1
            supabase.from("cart_product").upsert(
                value = item,
                onConflict = "id_cart,id_product"
            )

            Log.d("SupabaseRepo", "Producto agregado: ${product.name} (Cant: $newQuantity)")

        } catch (e: Exception) {
            Log.e("SupabaseRepo", "Error al agregar al carrito: ${e.message}")
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
