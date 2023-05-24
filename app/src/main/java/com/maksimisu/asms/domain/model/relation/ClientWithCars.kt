package com.maksimisu.asms.domain.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.maksimisu.asms.domain.model.Car
import com.maksimisu.asms.domain.model.Client

data class ClientWithCars(
    @Embedded
    val client: Client,

    @Relation(
        parentColumn = "id",
        entityColumn = "ownerId"
    )
    val cars: List<Car>?
)
