package com.ourbalance.domain.usecase

import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor() {
    private val emailRegex = Regex(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
          "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
          ")+"
    )

    operator fun invoke(input: String): Boolean {
        if (input.contains('@')) {
            return emailRegex.matches(input)
        }
        return false
    }
}
