package com.clpmodulith

import com.clpmodulith.security.oauth2.OAuth2Provider
import java.io.Serializable
import java.time.Instant

/**
 * DTO for {@link com.clpmodulith.user.model.Oauth2Detail}
 */
data class Oauth2DetailDto(
    val provider: OAuth2Provider,
    val id: String,
    val authenticatedAt: Instant
) : Serializable