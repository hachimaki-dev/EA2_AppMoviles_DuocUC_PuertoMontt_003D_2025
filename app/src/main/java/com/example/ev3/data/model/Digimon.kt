package com.example.ev3.data.model

import com.google.gson.annotations.SerializedName

// Response wrapper para la lista de Digimon
data class DigimonListResponse(
    val content: List<DigimonItem>,
    val pageable: Pageable
)

data class DigimonItem(
    val id: Int,
    val name: String,
    val href: String,
    val image: String
)

// Response para un Digimon individual
data class DigimonDetailResponse(
    val id: Int,
    val name: String,
    val xAntibody: Boolean,
    val images: List<DigimonImage>,
    val levels: List<DigimonLevel>,
    val types: List<DigimonType>?,
    val attributes: List<DigimonAttribute>?,
    val fields: List<DigimonField>?
)

data class DigimonImage(
    val href: String,
    val transparent: Boolean
)

data class DigimonLevel(
    val id: Int,
    val level: String
)

data class DigimonType(
    val id: Int,
    val type: String
)

data class DigimonAttribute(
    val id: Int,
    val attribute: String
)

data class DigimonField(
    val id: Int,
    val field: String,
    val image: String?
)

data class Pageable(
    val currentPage: Int,
    val elementsOnPage: Int,
    val totalElements: Int,
    val totalPages: Int,
    val previousPage: String?,
    val nextPage: String?
)

