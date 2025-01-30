package com.clpmodulith.security

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

/**
 * - 역할: 관리 {"jwt 프로퍼티","시크릿키" }
 * - 의존배제: 비지니스로직
 */
@Validated
@ConfigurationProperties(prefix = "security.jwt")
data class JwtProperties(
    @field: Size(min = 30)
    private val rawKey: String,
    @field: NotBlank
    private val algorithm: String,
    val durationSeconds: UInt,
) {
    val secretKey: SecretKey = SecretKeySpec(rawKey.toByteArray(), algorithm)

    init {
        checkAlgorithmOrThrow(algorithm)
    }

    override fun toString(): String {
        return "[hidden for security]"
    }

    private fun checkAlgorithmOrThrow(algorithm: String) {
        Cipher.getInstance(algorithm) //throw when invalid
    }
}
