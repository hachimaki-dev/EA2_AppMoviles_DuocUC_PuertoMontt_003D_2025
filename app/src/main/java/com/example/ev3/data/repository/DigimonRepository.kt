package com.example.ev3.data.repository

import com.example.ev3.data.model.DigimonDetailResponse
import com.example.ev3.data.model.DigimonItem
import com.example.ev3.data.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DigimonRepository(
    private val apiService: com.example.ev3.data.network.DigimonService = ApiClient.digimonService
) {
    suspend fun fetchDigimonList(page: Int = 0, pageSize: Int = 20): Result<List<DigimonItem>> =
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getDigimonList(page, pageSize)
                Result.success(response.content)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    suspend fun fetchDigimonDetail(id: Int): Result<DigimonDetailResponse> =
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getDigimonDetail(id)
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
}

