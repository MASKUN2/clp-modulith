package com.clpmodulith.security.oauth2

enum class Role(
    /**
     * Add Prefix "ROLE_": 스프링 시큐리티 granted Authority 네이밍 컨벤션에 맞춤
     */
    val authorityName: String
) {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    GUEST("ROLE_GUEST")


}