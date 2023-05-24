package com.maksimisu.asms.domain.usecase

import com.maksimisu.asms.domain.repository.ASMSRepository
import javax.inject.Inject

class GetAllWorkersPositionsUseCase @Inject constructor(
    private val asmsRepository: ASMSRepository
) {
    suspend operator fun invoke() = asmsRepository.getAllWorkersWithPositions()
}