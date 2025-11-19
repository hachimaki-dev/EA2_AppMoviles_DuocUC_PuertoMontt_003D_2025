package com.example.duocappmoviles003d.network

import com.example.duocappmoviles003d.catalog.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers(
        "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZ4bXdwa2FrcXpmb2h1aXl1YXlnIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjIwMjY4OTEsImV4cCI6MjA3NzYwMjg5MX0.sfO68JhLipTaxDKUr4s_2CfVI5x9vvWu2YJiTaPttj4",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZ4bXdwa2FrcXpmb2h1aXl1YXlnIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjIwMjY4OTEsImV4cCI6MjA3NzYwMjg5MX0.sfO68JhLipTaxDKUr4s_2CfVI5x9vvWu2YJiTaPttj4"
    )
    @GET("products")
    suspend fun getProducts(): Response<List<Product>>
}