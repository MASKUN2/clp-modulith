package com.clpmodulith.security

import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.*

/**
 * - 역할: Jwt를 다룹니다.
 * - 의존배제: 웹
 * - 의존: 시스템의 jwt 프로퍼티
 */
@Component
class JwtComponent(
    private val jwtProperties: JwtProperties
) {
    private val jwtParser = Jwts.parser().verifyWith(jwtProperties.secretKey).build()


    fun <T> getClaimValue(jwt: String, claimName: String, clazz: Class<T>): T? {
        return jwtParser.parseSignedClaims(jwt).payload
            .get(claimName, clazz)
    }

    fun makeJwt(
        from: Instant = Instant.now(),
        to: Instant = from.plusSeconds(jwtProperties.durationSeconds.toLong()),
        claims: Map<String, Any> = HashMap()
    ): String {
        require(from.isBefore(to)) { "The argument 'from' must be before 'to'. from= $from , to = $to " }

        return Jwts.builder()
            .issuedAt(Date.from(from))
            .expiration(Date.from(to))
            .claims().add(claims).and()
            .signWith(jwtProperties.secretKey)
            .compact()
    }
}