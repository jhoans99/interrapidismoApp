package com.sebas.interrapidismo.domain.usecases

import java.util.Base64
import javax.inject.Inject

class EncodeCredentialsUseCase @Inject constructor() {

    operator fun invoke(value: String): String {
        val bytes = value.toByteArray(Charsets.UTF_8)
        return "${Base64.getEncoder().encodeToString(bytes)}\n"
    }
}