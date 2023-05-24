package com.maksimisu.asms.domain.model.relation

import androidx.room.Entity

@Entity(primaryKeys = ["workerId", "positionId"])
data class WorkerPositionCrossRef(
    val workerId: Long,
    val positionId: Long
)