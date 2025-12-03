package com.example.ev3.repository

import com.example.ev3.data.model.*
import com.example.ev3.data.network.DigimonService
import com.example.ev3.data.repository.DigimonRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest

class DigimonRepositoryTest : FunSpec({

    lateinit var apiService: DigimonService
    lateinit var repository: DigimonRepository

    beforeEach {
        apiService = mockk()
        repository = DigimonRepository(apiService)
    }

    test("fetchDigimonList debe retornar lista exitosamente") {
        runTest {
            // Given
            val mockResponse = DigimonListResponse(
                content = listOf(
                    DigimonItem(1, "Agumon", "href1", "image1"),
                    DigimonItem(2, "Gabumon", "href2", "image2")
                ),
                pageable = Pageable(0, 2, 100, 50, null, "next")
            )
            coEvery { apiService.getDigimonList(0, 20) } returns mockResponse

            // When
            val result = repository.fetchDigimonList()

            // Then
            result.isSuccess shouldBe true
            result.getOrNull()?.size shouldBe 2
            result.getOrNull()?.first()?.name shouldBe "Agumon"
        }
    }

    test("fetchDigimonList debe manejar excepciones") {
        runTest {
            // Given
            coEvery { apiService.getDigimonList(any(), any()) } throws Exception("Network error")

            // When
            val result = repository.fetchDigimonList()

            // Then
            result.isFailure shouldBe true
            result.exceptionOrNull()?.message shouldBe "Network error"
        }
    }

    test("fetchDigimonDetail debe retornar detalle exitosamente") {
        runTest {
            // Given
            val mockDetail = DigimonDetailResponse(
                id = 1,
                name = "Agumon",
                xAntibody = false,
                images = listOf(DigimonImage("image.png", false)),
                levels = listOf(DigimonLevel(4, "Rookie")),
                types = null,
                attributes = null,
                fields = null
            )
            coEvery { apiService.getDigimonDetail(1) } returns mockDetail

            // When
            val result = repository.fetchDigimonDetail(1)

            // Then
            result.isSuccess shouldBe true
            result.getOrNull()?.name shouldBe "Agumon"
            result.getOrNull()?.levels?.first()?.level shouldBe "Rookie"
        }
    }

    test("fetchDigimonDetail debe manejar excepciones") {
        runTest {
            // Given
            coEvery { apiService.getDigimonDetail(999) } throws Exception("Not found")

            // When
            val result = repository.fetchDigimonDetail(999)

            // Then
            result.isFailure shouldBe true
            result.exceptionOrNull()?.message shouldBe "Not found"
        }
    }
})
