package com.maksimisu.asms.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Job(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val description: String,
    val price: Double,
    val startDate: String,
    val executionTime: String,
    val carVin: String,
    val clientId: Long,
    val workerId: Long
)
