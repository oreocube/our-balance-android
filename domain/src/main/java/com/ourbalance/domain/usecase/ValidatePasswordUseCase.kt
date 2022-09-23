package com.ourbalance.domain.usecase

import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor() {
    operator fun invoke(input: String): Boolean {
        return input.length in 6..15
    }
}
