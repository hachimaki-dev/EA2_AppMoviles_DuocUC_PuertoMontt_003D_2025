package com.example.ev3.data.network

import com.example.ev3.data.model.DigimonDetailResponse
import com.example.ev3.data.model.DigimonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DigimonService {
    @GET("api/v1/digimon")
    suspend fun getDigimonList(
        @Query("page") page: Int = 0,
        @Query("pageSize") pageSize: Int = 20
    ): DigimonListResponse

    @GET("api/v1/digimon/{id}")
    suspend fun getDigimonDetail(@Path("id") id: Int): DigimonDetailResponse
}

