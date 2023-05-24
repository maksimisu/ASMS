package com.maksimisu.asms.domain.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.maksimisu.asms.domain.model.Car
import com.maksimisu.asms.domain.model.Job

data class CarWithJobs(
    @Embedded
    val car: Car,

    @Relation(
        parentColumn = "vin",
        entityColumn = "carVin"
    )
    val jobs: List<Job>?
)
