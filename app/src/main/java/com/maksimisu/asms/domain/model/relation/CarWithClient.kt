package com.maksimisu.asms.domain.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.maksimisu.asms.domain.model.Car
import com.maksimisu.asms.domain.model.Client

data class CarWithClient(
    @Embedded
    val car: Car,

    @Relation(
        parentColumn = "ownerId",
        entityColumn = "id"
    )
    val client: Client?
)
