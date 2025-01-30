package com.clpmodulith

import com.clpmodulith.oauth2.Oauth2Provider
import java.io.Serializable
import java.time.Instant

/**
 * DTO for {@link com.clpmodulith.user.model.Oauth2Detail}
 */
data class Oauth2DetailDto(
    val provider: Oauth2Provider,
    val id: String,
    val authenticatedAt: Instant
) : Serializable