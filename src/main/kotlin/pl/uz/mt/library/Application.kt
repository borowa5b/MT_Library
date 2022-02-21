package pl.uz.mt.library

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("pl.uz.mt.library")
		.start()
}

