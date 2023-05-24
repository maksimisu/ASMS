package com.maksimisu.asms.domain.model.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.maksimisu.asms.domain.model.Position
import com.maksimisu.asms.domain.model.Worker

data class PositionWithWorkers(
    @Embedded
    val position: Position,

    @Relation(
        parentColumn = "positionId",
        entityColumn = "workerId",
        associateBy = Junction(WorkerPositionCrossRef::class)
    )
    val workers: List<Worker>?
)
