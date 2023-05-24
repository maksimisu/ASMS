package com.maksimisu.asms.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Car(
    @PrimaryKey
    val vin: String,
    val brand: String,
    val model: String,
    val year: Int,
    val ownerId: Long
)
