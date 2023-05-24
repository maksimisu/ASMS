package com.maksimisu.asms.domain.usecase

import com.maksimisu.asms.domain.model.relation.WorkerPositionCrossRef
import com.maksimisu.asms.domain.repository.ASMSRepository
import javax.inject.Inject

class AddWorkerPositionUseCase @Inject constructor(
    private val asmsRepository: ASMSRepository
) {
    suspend operator fun invoke(workerPositionCrossRef: WorkerPositionCrossRef) =
        asmsRepository.insertWorkerPosition(workerPositionCrossRef)
}