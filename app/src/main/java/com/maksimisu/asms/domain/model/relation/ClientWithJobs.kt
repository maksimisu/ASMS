package com.maksimisu.asms.domain.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.maksimisu.asms.domain.model.Client
import com.maksimisu.asms.domain.model.Job

data class ClientWithJobs(
    @Embedded
    val client: Client,

    @Relation(
        parentColumn = "id",
        entityColumn = "clientId"
    )
    val jobs: List<Job>?
)
