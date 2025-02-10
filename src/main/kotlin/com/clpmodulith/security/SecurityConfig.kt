package com.clpmodulith.security

import com.clpmodulith.security.authentication.CustomAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity(debug = false)
@Configuration
class SecurityConfig(
    val customAuthenticationFilter: CustomAuthenticationFilter,
    val authenticationSuccessHandler: AuthenticationSuccessHandler,
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            csrf {
                disable() // CSRF 비활성화 (필요 시 활성화 가능)
            }
            httpBasic {
                disable()
            }
            formLogin { // 폼 로그인 설정
                disable()
            }
            sessionManagement {
                sessionCreationPolicy = SessionCreationPolicy.STATELESS
            }
            authorizeHttpRequests {
                authorize("/".trim(), permitAll) // 루트 경로는 모두 접근 가능
                authorize("/error", permitAll) // 루트 경로는 모두 접근 가능
                authorize("/user/**", hasRole("USER")) // /user/** 경로는 USER 권한 필요
                authorize(anyRequest, authenticated) // 나머지는 인증 필요
            }
            logout {
                logoutUrl = "/logout"
            }
            http.addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
        }
        http.oauth2Login().successHandler(authenticationSuccessHandler)
        return http.build()
    }
}