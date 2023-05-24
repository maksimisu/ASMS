package com.maksimisu.asms.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Worker(
    @PrimaryKey(autoGenerate = true)
    val workerId: Long = 0,
    val name: String,
    val phone: String
)
