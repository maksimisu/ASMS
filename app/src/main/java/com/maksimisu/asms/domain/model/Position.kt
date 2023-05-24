package com.maksimisu.asms.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Position(
    @PrimaryKey(autoGenerate = true)
    val positionId: Long = 0,
    val jobTitle: String
)
