package com.maksimisu.asms.domain.usecase

import com.maksimisu.asms.domain.model.Car
import com.maksimisu.asms.domain.repository.ASMSRepository
import javax.inject.Inject

class AddCarUseCase @Inject constructor(
    private val asmsRepository: ASMSRepository
) {
    suspend operator fun invoke(car: Car) = asmsRepository.insertCar(car)
}