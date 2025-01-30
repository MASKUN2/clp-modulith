package com.clpmodulith

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan(basePackages = ["com.clpmodulith"])
@SpringBootApplication
class ClpModulithApplication

fun main(args: Array<String> ) {
    runApplication<ClpModulithApplication>(*args)

}
