package com.milka.doll_app

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<DollAppApplication>().with(TestcontainersConfiguration::class).run(*args)
}
