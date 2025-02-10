package com.clpmodulith

import com.clpmodulith.security.oauth2.Oauth2Naver
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("local")
@SpringBootTest
class ClpModulithApplicationTests {

    @Autowired
    lateinit var oauth2Naver: Oauth2Naver

    @Test
    fun contextLoads() {
        println(oauth2Naver.clientId)
    }

}
