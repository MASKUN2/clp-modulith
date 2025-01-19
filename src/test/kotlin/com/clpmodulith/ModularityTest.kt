package com.clpmodulith

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.modulith.core.ApplicationModules
import org.springframework.modulith.docs.Documenter
import org.springframework.modulith.test.ApplicationModuleTest

@ApplicationModuleTest
class ModularityTest {

    val modules = ApplicationModules.of(ClpModulithApplication::class.java)

    @BeforeEach fun setup() {
        // 모듈의 현황을 로그에 출력
        modules.forEach { println(it) }
    }

    @Test
    fun `명시되지 않은 모듈 의존이 없어야 한다`() {
        // 각 모듈이 선언된 의존 관계를 정확히 따르고 있는지 검사
        // (이 규칙을 어기는 의존이 있으면 예외가 발생)
        modules.verify()
    }

    @Test
    fun `모듈의 의존성 도표를 그린다`() {
        Documenter(modules).writeModulesAsPlantUml()
    }
}