package com.maksimisu.asms.domain.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.maksimisu.asms.domain.model.Job
import com.maksimisu.asms.domain.model.Worker

data class WorkerWithJobs(
    @Embedded
    val worker: Worker,

    @Relation(
        parentColumn = "workerId",
        entityColumn = "workerId"
    )
    val jobs: List<Job>?
)
