package com.maksimisu.asms.domain.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.maksimisu.asms.domain.model.Car
import com.maksimisu.asms.domain.model.Client
import com.maksimisu.asms.domain.model.Job
import com.maksimisu.asms.domain.model.Worker

data class JobWithCarAndClientAndWorker(
    @Embedded
    val job: Job,

    @Relation(
        parentColumn = "carVin",
        entityColumn = "vin"
    )
    val car: Car?,

    @Relation(
        parentColumn = "clientId",
        entityColumn = "id"
    )
    val client: Client?,

    @Relation(
        parentColumn = "workerId",
        entityColumn = "workerId"
    )
    val worker: Worker?
)
