package com.maksimisu.asms.domain.model.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.maksimisu.asms.domain.model.Position
import com.maksimisu.asms.domain.model.Worker

data class WorkerWithPositions(
    @Embedded
    val worker: Worker,

    @Relation(
        parentColumn = "workerId",
        entityColumn = "positionId",
        associateBy = Junction(WorkerPositionCrossRef::class)
    )
    val positions: List<Position>?
)
