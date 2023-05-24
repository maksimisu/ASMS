package com.maksimisu.asms.domain.usecase

import com.maksimisu.asms.domain.model.Position
import com.maksimisu.asms.domain.repository.ASMSRepository
import javax.inject.Inject

class UpdatePositionUseCase @Inject constructor(
    private val asmsRepository: ASMSRepository
) {
    suspend operator fun invoke(position: Position) = asmsRepository.updatePosition(position)
}