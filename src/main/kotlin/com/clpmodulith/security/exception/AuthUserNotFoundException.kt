package com.clpmodulith.security.exception

import org.springframework.security.core.AuthenticationException

class AuthUserNotFoundException(message: String) : AuthenticationException(message)
