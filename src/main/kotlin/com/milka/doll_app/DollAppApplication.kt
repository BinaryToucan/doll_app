package com.milka.doll_app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.sql.DriverManager

@SpringBootApplication
class DollAppApplication

fun main(args: Array<String>) {

    runApplication<DollAppApplication>(*args)
}
