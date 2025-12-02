import com.example.ferremas.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("products?select=*")
    suspend fun getProducts(): Response<List<Product>>
}
