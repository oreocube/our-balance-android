package com.ourbalance.domain.usecase

import com.ourbalance.domain.repository.AuthRepository
import javax.inject.Inject

class CheckLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Boolean {
        return authRepository.checkLogin()
    }
}
