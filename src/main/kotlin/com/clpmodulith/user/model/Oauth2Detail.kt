package com.clpmodulith.user.model

import com.clpmodulith.oauth2.Oauth2Provider
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.time.Instant

@Embeddable
class Oauth2Detail (
    @Enumerated(EnumType.STRING)
    @Column(name = "provider")
    val provider: Oauth2Provider,

    @Column(name = "id")
    val id: String,

    @Column(name = "authenticatedAt")
    val authenticatedAt: Instant
)