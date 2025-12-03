package com.example.ev3.viewmodel

import com.example.ev3.data.model.DigimonItem
import com.example.ev3.data.model.DigimonDetailResponse
import com.example.ev3.data.model.DigimonImage
import com.example.ev3.data.model.DigimonLevel
import com.example.ev3.data.repository.DigimonRepository
import com.example.ev3.ui.viewmodel.DigimonViewModel
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*

@OptIn(ExperimentalCoroutinesApi::class)
class DigimonViewModelTest : StringSpec({

    lateinit var repository: DigimonRepository
    lateinit var viewModel: DigimonViewModel
    val testDispatcher = StandardTestDispatcher()

    beforeEach {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
    }

    afterEach {
        Dispatchers.resetMain()
    }

    "ViewModel debe cargar lista de Digimon exitosamente" {
        // Given
        val mockDigimonList = listOf(
            DigimonItem(1, "Agumon", "https://digi-api.com/api/v1/digimon/1", "https://digi-api.com/images/digimon/w/Agumon.png"),
            DigimonItem(2, "Gabumon", "https://digi-api.com/api/v1/digimon/2", "https://digi-api.com/images/digimon/w/Gabumon.png")
        )
        coEvery { repository.fetchDigimonList(any(), any()) } returns Result.success(mockDigimonList)

        // When
        viewModel = DigimonViewModel(repository)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val state = viewModel.listUiState.value
        state.isLoading shouldBe false
        state.digimonList.size shouldBe 2
        state.error shouldBe null
        state.digimonList[0].name shouldBe "Agumon"
    }

    "ViewModel debe manejar error al cargar lista" {
        // Given
        val errorMessage = "Error de conexión"
        coEvery { repository.fetchDigimonList(any(), any()) } returns Result.failure(Exception(errorMessage))

        // When
        viewModel = DigimonViewModel(repository)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val state = viewModel.listUiState.value
        state.isLoading shouldBe false
        state.digimonList.size shouldBe 0
        state.error shouldNotBe null
        state.error shouldBe errorMessage
    }

    "ViewModel debe cargar detalle de Digimon exitosamente" {
        // Given
        val mockDetail = DigimonDetailResponse(
            id = 1,
            name = "Agumon",
            xAntibody = false,
            images = listOf(DigimonImage("https://digi-api.com/images/digimon/w/Agumon.png", false)),
            levels = listOf(DigimonLevel(4, "Rookie")),
            types = null,
            attributes = null,
            fields = null
        )
        coEvery { repository.fetchDigimonList(any(), any()) } returns Result.success(emptyList())
        coEvery { repository.fetchDigimonDetail(1) } returns Result.success(mockDetail)

        viewModel = DigimonViewModel(repository)
        testDispatcher.scheduler.advanceUntilIdle()

        // When
        viewModel.loadDigimonDetail(1)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val state = viewModel.detailUiState.value
        state.isLoading shouldBe false
        state.digimon shouldNotBe null
        state.digimon?.name shouldBe "Agumon"
        state.digimon?.levels?.first()?.level shouldBe "Rookie"
        state.error shouldBe null
    }

    "ViewModel debe manejar error al cargar detalle" {
        // Given
        val errorMessage = "Digimon no encontrado"
        coEvery { repository.fetchDigimonList(any(), any()) } returns Result.success(emptyList())
        coEvery { repository.fetchDigimonDetail(999) } returns Result.failure(Exception(errorMessage))

        viewModel = DigimonViewModel(repository)
        testDispatcher.scheduler.advanceUntilIdle()

        // When
        viewModel.loadDigimonDetail(999)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val state = viewModel.detailUiState.value
        state.isLoading shouldBe false
        state.digimon shouldBe null
        state.error shouldBe errorMessage
    }

    "Retry debe volver a cargar la lista" {
        // Given
        val mockDigimonList = listOf(
            DigimonItem(1, "Agumon", "https://digi-api.com/api/v1/digimon/1", "https://digi-api.com/images/digimon/w/Agumon.png")
        )
        coEvery { repository.fetchDigimonList(any(), any()) } returns Result.success(mockDigimonList)

        viewModel = DigimonViewModel(repository)
        testDispatcher.scheduler.advanceUntilIdle()

        // When
        viewModel.retry()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val state = viewModel.listUiState.value
        state.isLoading shouldBe false
        state.digimonList.size shouldBe 1
    }
})
