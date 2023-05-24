package com.maksimisu.asms.domain.usecase

import com.maksimisu.asms.domain.model.Worker
import com.maksimisu.asms.domain.repository.ASMSRepository
import javax.inject.Inject

class UpdateWorkerUseCase @Inject constructor(
    private val asmsRepository: ASMSRepository
) {
    suspend operator fun invoke(worker: Worker) = asmsRepository.updateWorker(worker)
}