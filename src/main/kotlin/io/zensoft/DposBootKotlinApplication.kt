package io.zensoft

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DposBootKotlinApplication

fun main(args: Array<String>) {
    runApplication<DposBootKotlinApplication>(*args)
}
