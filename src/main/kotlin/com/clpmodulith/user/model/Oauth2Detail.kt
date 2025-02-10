package com.clpmodulith.user.model

import com.clpmodulith.security.oauth2.OAuth2Provider
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.time.Instant

@Embeddable
class Oauth2Detail (
    @Enumerated(EnumType.STRING)
    @Column(name = "provider")
    val provider: OAuth2Provider,

    @Column(name = "id")
    val id: String,

    @Column(name = "authenticatedAt")
    val authenticatedAt: Instant
)