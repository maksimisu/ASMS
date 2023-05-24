package com.maksimisu.asms.domain.usecase

import com.maksimisu.asms.domain.model.Job
import com.maksimisu.asms.domain.repository.ASMSRepository
import javax.inject.Inject

class UpdateJobUseCase @Inject constructor(
    private val asmsRepository: ASMSRepository
) {
    suspend operator fun invoke(job: Job) = asmsRepository.updateJob(job)
}