package com.maksimisu.asms.domain.usecase

import com.maksimisu.asms.domain.model.Client
import com.maksimisu.asms.domain.repository.ASMSRepository
import javax.inject.Inject

class AddClientUseCase @Inject constructor(
    private val asmsRepository: ASMSRepository
) {
    suspend operator fun invoke(client: Client) = asmsRepository.insertClient(client)
}