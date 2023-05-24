package com.maksimisu.asms.domain.usecase

import com.maksimisu.asms.domain.model.Car
import com.maksimisu.asms.domain.repository.ASMSRepository
import javax.inject.Inject

class UpdateCarUseCase @Inject constructor(
    private val asmsRepository: ASMSRepository
) {
    suspend operator fun invoke(car: Car) = asmsRepository.updateCar(car)
}